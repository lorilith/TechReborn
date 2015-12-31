package techreborn.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import techreborn.api.reactor.FusionReactorRecipeHelper;
import techreborn.api.recipe.RecipeHandler;
import techreborn.client.container.ContainerAlloyFurnace;
import techreborn.client.container.ContainerAlloySmelter;
import techreborn.client.container.ContainerFusionReactor;
import techreborn.compat.jei.alloySmelter.AlloySmelterRecipeCategory;
import techreborn.compat.jei.alloySmelter.AlloySmelterRecipeHandler;
import techreborn.compat.jei.fusionReactor.FusionReactorRecipeCategory;
import techreborn.compat.jei.fusionReactor.FusionReactorRecipeHandler;

@mezz.jei.api.JEIPlugin
public class TechRebornJeiPlugin implements IModPlugin {
    public static IJeiHelpers jeiHelpers;

    @Override
    public boolean isModLoaded() {
        return true;
    }

    @Override
    public void onJeiHelpersAvailable(IJeiHelpers jeiHelpers) {
        TechRebornJeiPlugin.jeiHelpers = jeiHelpers;
    }

    @Override
    public void onItemRegistryAvailable(IItemRegistry itemRegistry) {

    }

    @Override
    public void register(IModRegistry registry) {
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registry.addRecipeCategories(
                new AlloySmelterRecipeCategory(guiHelper),
                new FusionReactorRecipeCategory(guiHelper)
        );

        registry.addRecipeHandlers(
                new AlloySmelterRecipeHandler(),
                new FusionReactorRecipeHandler()
        );

        registry.addRecipes(RecipeHandler.recipeList);
        registry.addRecipes(FusionReactorRecipeHelper.reactorRecipes);

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
        recipeTransferRegistry.addRecipeTransferHandler(ContainerAlloyFurnace.class, RecipeCategoryUids.ALLOY_SMELTER, 0, 2, 4, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerAlloySmelter.class, RecipeCategoryUids.ALLOY_SMELTER, 0, 2, 8, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerAlloyFurnace.class, VanillaRecipeCategoryUid.FUEL, 3, 1, 4, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerFusionReactor.class, RecipeCategoryUids.FUSION_REACTOR, 0, 2, 3, 36);
    }

    @Override
    public void onRecipeRegistryAvailable(IRecipeRegistry recipeRegistry) {

    }
}
