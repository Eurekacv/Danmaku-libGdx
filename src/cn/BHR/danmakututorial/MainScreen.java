package cn.BHR.danmakututorial;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import cn.BHR.danmakututorial.entities.projectiles.SimpleRedBullet;
import cn.BHR.danmakututorial.entities.*;
import cn.BHR.danmakututorial.entities.npcs.NPCAndroid;
import cn.BHR.danmakututorial.entities.players.PlayerAlice;

public class MainScreen extends ScreenAdapter {
	public static int Width, Height;
	public static Stage Stage;
	public static Group MainGroup;
	public static Rectangle FightArea;
	public static InputMultiplexer InputMgr;
	@Override
	public void show() {
		Stage = new Stage(new ScalingViewport(Scaling.fit, 540f, 720f), Main.SBatch);
		Width = 540;
		Height = 720;
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.DARK_GRAY);
		pixmap.fill();
		Image bg = new Image(new Texture(pixmap));
		bg.setBounds(0, 0, 540, 720);
		Stage.addActor(bg);
		
		MainGroup = new Group();
		Stage.addActor(MainGroup);
		
		FightArea = new Rectangle(0, 0, 540, 720);

		new PlayerAlice().Init();
		NPCAndroid.Pool.obtain().Init(new Vector2(270, 540));
		InputMgr = new InputMultiplexer();
		InputMgr.addProcessor(new Player.PlayerInputProcessor());
		Gdx.input.setInputProcessor(InputMgr);
		
		super.show();
	}
	
	@Override
	public void render(float delta) {
		Projectile.UpdateAll();
		Player.Instance.Update();
		Stage.draw();
		super.render(delta);
	}
	
	@Override
	public void hide() {
		super.hide();
	}
}
