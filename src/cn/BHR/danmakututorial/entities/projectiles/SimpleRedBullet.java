package cn.BHR.danmakututorial.entities.projectiles;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;

import cn.BHR.danmakututorial.entities.Projectile;
import cn.BHR.danmakututorial.helpers.Resources;

public class SimpleRedBullet extends Projectile
{
	public static Pool<SimpleRedBullet> Pool = new Pool<SimpleRedBullet>()
			{
				@Override
				protected SimpleRedBullet newObject() {
					return new SimpleRedBullet();
				}
			};
	Circle judgeCircle;
	static Drawable drawable;
	public Vector2 Size = new Vector2();
	public void Init(Vector2 center, Vector2 velocity)
	{
		super.Init();
		Size.set(24, 24);
		Drawer.setSize(Size.x, Size.y);
		judgeCircle = new Circle(Center, Drawer.getWidth() / 2); //中心、半径
		Center.set(center);
		Velocity.set(velocity);
		Drawer.setPosition(center.x, center.y, Align.center);
	}
	@Override
	public void Update()
	{
		judgeCircle.setPosition(Center);
		super.Update();
	}
	@Override
	protected Shape2D getCollisionArea()
	{
		return judgeCircle;
	}
	@Override
	protected Drawable getDrawable()
	{
		if (drawable == null)
			drawable = Resources.ProjDrawables.get("SimpleRedBullet");
		return drawable;
	}
}
