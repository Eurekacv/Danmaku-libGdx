package cn.BHR.danmakututorial.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

import cn.BHR.danmakututorial.MainScreen;
import cn.BHR.danmakututorial.helpers.Pools;

public abstract class BaseEntity
{
	public Vector2 Center = new Vector2();
	public Vector2 Velocity = new Vector2();
	public Image Drawer = null;
	public int ExistTime;
	public void Init()
	{
		//获取一个Image
		Drawer = Pools.ImagePool.obtain();
		Drawable drawable = getDrawable();
		//设置材质
		Drawer.setDrawable(drawable);
		//加入舞台中
		MainScreen.MainGroup.addActor(Drawer);
		ExistTime = 0;
	}
	public void Kill()
	{
		Drawer.remove();
		//将Drawer从舞台上撤下并且将其回归到Pool中
		Pools.ImagePool.free(Drawer);
	}
	public void Update()
	{
		//更新位置
		Center.add(Velocity);
		Drawer.setPosition(Center.x, Center.y, Align.center);
		ExistTime++;
	}
	protected abstract Drawable getDrawable();
}
