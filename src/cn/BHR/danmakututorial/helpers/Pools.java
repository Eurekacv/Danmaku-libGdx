package cn.BHR.danmakututorial.helpers;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Pool;

public final class Pools
{
	public static Pool<Image> ImagePool = new Pool<Image>(500)
			{
				@Override
				protected Image newObject() {
					return new Image();
				}
			};
}
