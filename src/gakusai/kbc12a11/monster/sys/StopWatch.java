package gakusai.kbc12a11.monster.sys;

import java.util.Date;


public class StopWatch {
	protected long startTime;

	protected long tet;//トータル経過時間

	protected int state = STOP;
	protected static final int STOP = 0;
	protected static final int START = 1;

	/**ストップウォッチをリセットする*/
	public void reset() {
		startTime = -1;
		tet = 0;
	}

	/**カウントをスタートする*/
	public void start() {
		if (state == START) return;
		startTime = new Date().getTime();
		setState(START);
	}

	/**時間計測を一時停止*/
	public void pause() {
		if (state == STOP) return;
		tet += new Date().getTime() - startTime;
		setState(STOP);
	}

	public long getTime() {
		if (state == STOP) {
			return tet;
		}else {
			return new Date().getTime() - startTime + tet;
		}
	}

	private void setState(int state) {
		this.state = state;
	}
}
