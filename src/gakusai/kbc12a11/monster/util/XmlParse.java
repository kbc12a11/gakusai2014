package gakusai.kbc12a11.monster.util;

import gakusai.kbc12a11.monster.sys.Map;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.newdawn.slick.SlickException;

public class XmlParse {
	public static void test(String test) {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(test);
			List nodes = document.selectNodes("/map/@version|/map/@width");

			for(Iterator i = nodes.iterator(); i.hasNext();) {
				Node node = (Node) i.next();
				System.out.println("job:" + node.getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**.tmxファイルからマップデータを取得する
	 * @throws SlickException */
	public static Map getMap(String mapName) throws SlickException {
		SAXReader reader = new SAXReader();
		Map map = null;
		try {
			Document document = reader.read(mapName);
			String s = "/map/@width|/map/@height";//マップの幅と高さ
			s += "|/map/@tilewidth|/map/@tileheight";//マップチップのスクリーン上の幅と高さ
			s += "|/map/tileset/@tilewidth|/map/tileset/@tileheight";//マップチップの画像から切り取る幅と高さ
			s += "|/map/tileset/image/@source";//マップチップの画像ソース
			s += "|/map/tileset/image/@width|/map/tileset/image/@height";//マップチップ用画像の幅と高さ
			s += "|/map/layer/data";//マップデータ

			List<Node> nodes = document.selectNodes(s);
			Iterator<Node> itr = nodes.iterator();

			int width = Integer.parseInt(itr.next().getText());
			int height = Integer.parseInt(itr.next().getText());

			int tilescrwidth = Integer.parseInt(itr.next().getText());
			int tilescrheight = Integer.parseInt(itr.next().getText());

			int tilechipwidth = Integer.parseInt(itr.next().getText());
			int tilechipheight = Integer.parseInt(itr.next().getText());

			String imageName = itr.next().getText();
			int imageWidth = Integer.parseInt(itr.next().getText());
			int imageHeight = Integer.parseInt(itr.next().getText());

			String tmp = itr.next().getText();
			tmp = tmp.trim();
			String[] str = tmp.split("\n*,\n*");
			int[] data = new int[width * height];
			int j = 0;
			for (String s0 : str) {
				data[j++] = Integer.parseInt(s0);
			}

			map = new Map(data, width, height);
			map.setChipSize(tilechipwidth, tilechipheight);
			map.setChipSizeOnScreen(tilescrwidth, tilescrheight);
			//map.setMapImage(imageName, imageWidth, imageHeight);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return map;
	}
}
