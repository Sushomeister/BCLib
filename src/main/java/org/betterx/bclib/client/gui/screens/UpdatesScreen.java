package org.betterx.bclib.client.gui.screens;

import org.betterx.bclib.config.Configs;
import org.betterx.bclib.networking.VersionChecker;
import org.betterx.ui.ColorUtil;
import org.betterx.ui.layout.components.HorizontalStack;
import org.betterx.ui.layout.components.LayoutComponent;
import org.betterx.ui.layout.components.VerticalStack;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

@Environment(EnvType.CLIENT)
public class UpdatesScreen extends BCLibLayoutScreen {

    public static final String DONATION_URL = "https://www.paypal.com/donate/?hosted_button_id=7VTXYRXBHZQZJ&item_name=BetterX%20Mods&cmd=_s-xclick";

    public UpdatesScreen(Screen parent) {
        super(parent, Component.translatable("bclib.updates.title"), 10, 10, 10);
    }

    @Override
    protected LayoutComponent<?, ?> initContent() {
        VerticalStack rows = new VerticalStack(relative(1), fit()).centerHorizontal();
        rows.addMultilineText(fill(), fit(), Component.translatable("bclib.updates.description"))
            .centerHorizontal();

        rows.addSpacer(16);

        VersionChecker.forEachUpdate((mod, cur, updated) -> {
            ModContainer nfo = FabricLoader.getInstance().getModContainer(mod).orElse(null);

            HorizontalStack row = rows.addRow(relative(0.8), fit()).centerHorizontal();
            if (nfo != null) {
                row.addText(fit(), fit(), Component.literal(nfo.getMetadata().getName()))
                   .setColor(ColorUtil.WHITE);
            } else {
                row.addText(fit(), fit(), Component.literal(mod)).setColor(ColorUtil.WHITE);
            }
            row.addSpacer(4);
            row.addText(fit(), fit(), Component.literal(cur));
            row.addText(fit(), fit(), Component.literal(" -> "));
            row.addText(fit(), fit(), Component.literal(updated)).setColor(ColorUtil.GREEN);
            row.addFiller();
            if (nfo != null && nfo.getMetadata().getContact().get("homepage").isPresent()) {
                row.addButton(fit(), fit(), Component.translatable("bclib.updates.curseforge_link"))
                   .onPress((bt) -> {
                       this.openLink(nfo.getMetadata().getContact().get("homepage").get());
                   });
            }
        });

        VerticalStack layout = new VerticalStack(relative(1), fill()).centerHorizontal();
        layout.addSpacer(8);
        layout.addScrollable(rows);
        layout.addSpacer(8);


        HorizontalStack footer = layout.addRow(fill(), fit());
        if (Configs.CLIENT_CONFIG.isDonor()) {
            footer.addButton(
                          fit(),
                          fit(),
                          Component.translatable("bclib.updates.donate").setStyle(Style.EMPTY.withColor(ColorUtil.YELLOW))
                  )
                  .onPress((bt) -> openLink(DONATION_URL));
            footer.addSpacer(2);
            footer.addMultilineText(fit(), fit(), Component.translatable("bclib.updates.donate_pre"))
                  .alignBottom();
        }

        footer.addFiller();
        footer.addCheckbox(
                      fit(),
                      fit(),
                      Component.translatable("Disable Check"),
                      !Configs.MAIN_CONFIG.checkVersions()
              )
              .onChange((cb, state) -> {
                  Configs.MAIN_CONFIG.setCheckVersions(!state);
                  Configs.MAIN_CONFIG.saveChanges();
              });
        footer.addSpacer(4);
        footer.addButton(fit(), fit(), CommonComponents.GUI_DONE).onPress((bt -> {
            onClose();
        }));
        return layout;
    }

    @Override
    protected void renderBackground(PoseStack poseStack, int i, int j, float f) {
        GuiComponent.fill(poseStack, 0, 0, width, height, 0xBD343444);
    }
}
