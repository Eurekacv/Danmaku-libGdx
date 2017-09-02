package cn.BHR.danmakututorial.entities;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.math.*;

import cn.BHR.danmakututorial.MainScreen;

public abstract class Projectile extends BaseEntity
{
	public static HashSet<Projectile> Instances = new HashSet<Projectile>();
	public static LinkedBlockingQueue<Projectile> ToDelete = new LinkedBlockingQueue<Projectile>();
	public static LinkedBlockingQueue<Projectile> ToAdd = new LinkedBlockingQueue<Projectile>();
	//建立一个对所有Projectile的引用
	
	protected Rectangle drawBox = new Rectangle();
	@Override
	public void Init()
	{
		super.Init();
		ToAdd.add(this);
	}
	@Override
	public void Update()
	{
		drawBox.set(Drawer.getX(), Drawer.getY(), Drawer.getWidth(), Drawer.getHeight());
		if (!drawBox.overlaps(MainScreen.FightArea))
			Kill();
		super.Update();
		Judge();
	}
	
	protected abstract Shape2D getCollisionArea();
	
	public void Judge()
	{
		if (getCollisionArea().contains(Player.Instance.Center))
		{
			Player.Instance.Kill();
		}
	}
	
	@Override
	public void Kill()
	{
		ToDelete.add(this);
		//在这里直接remove会报ConcurrentModification异常，加入队列中等待下一帧开始时处理
		super.Kill();
	}
	
	public static void UpdateAll()
	{
		while (!ToDelete.isEmpty())
			Instances.remove(ToDelete.poll());
		while (!ToAdd.isEmpty())
			Instances.add(ToAdd.poll());
		for (Projectile projectile : Instances)
		{
			projectile.Update();
		}
	}
}
