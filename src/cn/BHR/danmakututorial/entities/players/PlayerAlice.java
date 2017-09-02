package cn.BHR.danmakututorial.entities.players;
import cn.BHR.danmakututorial.entities.*;
import cn.BHR.danmakututorial.helpers.Resources;

import com.badlogic.gdx.scenes.scene2d.utils.*;

public class PlayerAlice extends Player
{
	public static class AliceEntity extends BaseEntity
	{
		@Override
		protected Drawable getDrawable()
		{
			return Resources.PlrDrawables.get("Alice");
		}
		public void Init()
		{
			super.Init();
			Drawer.setSize(49, 95);
		}
		@Override
		public void Update()
		{
			Drawer.toBack();
			this.Center.set(Player.Instance.Center);
			super.Update();
		}
	}
	AliceEntity Alice;
	@Override
	public void Init()
	{
		Alice = new AliceEntity();
		Alice.Init();
		super.Init();
	}
	
	@Override
	public void Update()
	{
		super.Update();
		Alice.Update();
	}
	
	/*@Override
	public void Kill()
	{
		Alice.Kill();
		super.Kill();
	}*/
	
}
