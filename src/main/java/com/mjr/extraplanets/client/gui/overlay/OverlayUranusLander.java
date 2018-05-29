package com.mjr.extraplanets.client.gui.overlay;

import micdoodle8.mods.galacticraft.core.client.gui.overlay.Overlay;
import micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.mjr.extraplanets.entities.landers.EntityUranusLander;
import com.mjr.mjrlegendslib.util.MCUtilities;
import com.mjr.mjrlegendslib.util.TranslateUtilities;

@SideOnly(Side.CLIENT)
public class OverlayUranusLander extends Overlay {
	private static Minecraft minecraft = MCUtilities.getClient();

	private static long screenTicks;

	public static void renderLanderOverlay() {
		OverlayUranusLander.screenTicks++;
		final ScaledResolution scaledresolution = ClientUtil.getScaledRes(minecraft, OverlayUranusLander.minecraft.displayWidth, OverlayUranusLander.minecraft.displayHeight);
		final int width = scaledresolution.getScaledWidth();
		final int height = scaledresolution.getScaledHeight();
		OverlayUranusLander.minecraft.entityRenderer.setupOverlayRendering();

		GL11.glPushMatrix();

		GL11.glScalef(2.0F, 2.0F, 0.0F);

		if (OverlayUranusLander.minecraft.player.getRidingEntity().motionY < -2.0) {
			OverlayUranusLander.minecraft.fontRenderer.drawString(TranslateUtilities.translate("gui.warning"), width / 4 - OverlayUranusLander.minecraft.fontRenderer.getStringWidth(TranslateUtilities.translate("gui.warning")) / 2, height / 8 - 20,
					ColorUtil.to32BitColor(255, 255, 0, 0));
			final int alpha = (int) (255 * Math.sin(OverlayUranusLander.screenTicks / 20.0F));
			final String press1 = TranslateUtilities.translate("gui.lander.warning2");
			final String press2 = TranslateUtilities.translate("gui.lander.warning3");
			OverlayUranusLander.minecraft.fontRenderer
					.drawString(press1 + GameSettings.getKeyDisplayString(KeyHandlerClient.spaceKey.getKeyCode()) + press2,
							width / 4 - OverlayUranusLander.minecraft.fontRenderer.getStringWidth(press1 + GameSettings.getKeyDisplayString(KeyHandlerClient.spaceKey.getKeyCode()) + press2) / 2, height / 8,
							ColorUtil.to32BitColor(alpha, alpha, alpha, alpha));
		}

		GL11.glPopMatrix();

		if (OverlayUranusLander.minecraft.player.getRidingEntity().motionY != 0.0D) {
			String string = TranslateUtilities.translate("gui.lander.velocity") + ": " + Math.round(((EntityUranusLander) OverlayUranusLander.minecraft.player.getRidingEntity()).motionY * 1000) / 100.0D + " "
					+ TranslateUtilities.translate("gui.lander.velocityu");
			int color = ColorUtil.to32BitColor(255, (int) Math.floor(Math.abs(OverlayUranusLander.minecraft.player.getRidingEntity().motionY) * 51.0D),
					255 - (int) Math.floor(Math.abs(OverlayUranusLander.minecraft.player.getRidingEntity().motionY) * 51.0D), 0);
			OverlayUranusLander.minecraft.fontRenderer.drawString(string, width / 2 - OverlayUranusLander.minecraft.fontRenderer.getStringWidth(string) / 2, height / 3, color);
		}
	}
}
