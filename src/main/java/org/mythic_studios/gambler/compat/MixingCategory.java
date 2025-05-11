package org.mythic_studios.gambler.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.init.alchohol.AlcoholBlocks;

import java.util.LinkedList;
import java.util.List;

public class MixingCategory implements DisplayCategory<MixingDisplay> {
    public static final Identifier TEXTURE = Identifier.of(GamblersDreamAlcohol.MOD_ID,
            "textures/gui/ingredient_mixer/ingredient_mixer_gui.png");
    public static final CategoryIdentifier<MixingDisplay> MIXER =
            CategoryIdentifier.of(GamblersDreamAlcohol.MOD_ID, "ingredient_mixer");

    @Override
    public CategoryIdentifier<? extends MixingDisplay> getCategoryIdentifier() {
        return MIXER;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.gambler.ingredient_mixer");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(AlcoholBlocks.INGREDIENT_MIXER.asItem().getDefaultStack());
    }

    // Done with the help:
    // https://github.com/TeamGalacticraft/Galacticraft/tree/main (MIT License)
    @Override
    public List<Widget> setupDisplay(MixingDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        // Safely add input slots with null checks and size validation
        List<EntryIngredient> inputs = display.getInputEntries();
            if (!inputs.isEmpty()) {
                widgets.add(Widgets.createSlot(new Point(startPoint.x + 53, startPoint.y + 14))
                        .entries(inputs.get(0)).markInput());
            }
            if (inputs.size() >= 2) {
                widgets.add(Widgets.createSlot(new Point(startPoint.x + 53, startPoint.y + 56))
                        .entries(inputs.get(1)).markInput());
            }


        // Safely add output slot with null checks and size validation
        List<EntryIngredient> outputs = display.getOutputEntries();
        if (outputs != null && !outputs.isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 104, startPoint.y + 34))
                    .entries(outputs.getFirst()).markOutput());
        }

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}