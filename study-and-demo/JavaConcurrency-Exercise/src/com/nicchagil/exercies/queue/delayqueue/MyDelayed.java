package com.nicchagil.exercies.queue.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelayed implements Delayed {
	
	private Long delayTime; // 延迟的时间
	private Long expiringTime; // 延迟后的到期时间
	
	public MyDelayed(Long delayTime) {
		super();
		this.delayTime = delayTime;
		this.expiringTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.NANOSECONDS); // 计算延迟后的到期时间
	}

	@Override
	public int compareTo(Delayed o) {
        return delayTime.compareTo(((MyDelayed)o).getDelayTime()); // 根据时间进行排序
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(expiringTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	public Long getDelayTime() {
		return delayTime;
	}

	@Override
	public String toString() {
		return "MyDelayed [delayTime=" + delayTime + ", expiringTime=" + expiringTime + "]";
	}
	
}
