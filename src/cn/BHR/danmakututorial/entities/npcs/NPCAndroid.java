package cn.BHR.danmakututorial.entities.npcs;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;

import cn.BHR.danmakututorial.entities.NPC;
import cn.BHR.danmakututorial.entities.projectiles.SimpleRedBullet;
import cn.BHR.danmakututorial.helpers.Resources;
public class NPCAndroid extends NPC {
	public static Pool<NPCAndroid> Pool = new Pool<NPCAndroid>()
	{
		@Override
		protected NPCAndroid newObject() {
			return new NPCAndroid();
		}
	};
	
	public void Init(Vector2 center) {
		super.Init();
		Center.set(center);
		Velocity.set(0, 0);
		Drawer.setSize(100, 121);
		judgeCircle.set(Center, 40);
		Drawer.setPosition(center.x, center.y, Align.center);
	}
	
	@Override
	public int GetMaxLife() {
		return 10000;
	}
	

	float f_angleCurrent = 0;
	boolean b_angleWay = true;
	Vector2 vct_pos = new Vector2(), vct_spd = new Vector2(), vct_tmp1 = new Vector2();
	@Override
	public void Update() {
		super.Update();
		if (ExistTime % 30 == 1) {
			int randVal = MathUtils.random(0, 360);
			Vector2 vel = new Vector2(3, 0);
			vel.rotate(randVal);
			for (int i = 0; i < 12; i++) {
				SimpleRedBullet.Pool.obtain().Init(Center, vel);
				vel.rotate(30);
			}
		}
		if (ExistTime % 3 == 1)
		{
			if (f_angleCurrent > 60)
				b_angleWay = false;
			else if (f_angleCurrent < 0)
				b_angleWay = true;
			if (b_angleWay)
				f_angleCurrent += 6;
			else f_angleCurrent -= 6;
			vct_tmp1.set(90, 0).rotate(f_angleCurrent + 240);
			vct_pos.set(270, 540).add(vct_tmp1);
			vct_spd.set(2, 0).rotate(f_angleCurrent + 240);
			SimpleRedBullet bullet = SimpleRedBullet.Pool.obtain();
			bullet.Init(vct_pos, vct_spd);
		}
	}
	Circle judgeCircle = new Circle();
	@Override
	protected Shape2D getCollisionArea() {
		return judgeCircle;
	}

	@Override
	protected Drawable getDrawable() {
		return Resources.NPCDrawables.get("Android");
	}
}
