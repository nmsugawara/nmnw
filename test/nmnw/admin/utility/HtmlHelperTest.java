package nmnw.admin.utility;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.nmnw.admin.utility.HtmlHelper;

public class HtmlHelperTest {

	@Test
	public void nl2brTest() {
		/**
		 * 変換有の場合
		 */
		// 初期化
		String expectedConverted = "test<br>test";
		String valueConverted = "test\ntest";
		// 実行
		String actualConverted = HtmlHelper.nl2br(valueConverted);
		// 検証
		assertThat("nl2br:変換有", actualConverted, is(expectedConverted));
		// 後処理

		/**
		 * 変換有の場合
		 */
		// 初期化
		String expectedNotConverted = "testntest";
		String valueNotConverted = "testntest";
		// 実行
		String actualNotConverted = HtmlHelper.nl2br(valueNotConverted);
		// 検証
		assertThat("nl2br:変換無", actualNotConverted, is(expectedNotConverted));
		// 後処理
	}

	@Test
	public void htmlspecialcharsTest() {
		/**
		 * 変換有の場合
		 */
		// 初期化
		String expectedConverted = "&amp; &lt; &gt; &quot; &#39; <br> &#x0009;";
		String valueConverted = "& < > \" \' \n \t";
		// 実行
		String actualConverted = HtmlHelper.htmlspecialchars(valueConverted);
		// 検証
		assertThat("htmlspecialchars:変換有", actualConverted, is(expectedConverted));
		// 後処理

		/**
		 * 変換無の場合
		 */
		// 初期化
		String expectedNotConverted = "test";
		String valueNotConverted = "test";
		// 実行
		String actualNotConverted = HtmlHelper.htmlspecialchars(valueNotConverted);
		// 検証
		assertThat("htmlspecialchars:変換無", actualNotConverted, is(expectedNotConverted));
		// 後処理
	}
}
