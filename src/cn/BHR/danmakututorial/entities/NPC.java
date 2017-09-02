package cn.BHR.danmakututorial.entities;

public abstract class NPC extends Projectile {
	public int Life;
	@Override
	public void Init() {
		super.Init();
		Life = GetMaxLife();
	}
	@Override
	public void Update() {
		super.Update();
		if (Life <= 0) {
			Kill();
		}
	}
	public abstract int GetMaxLife();
}
