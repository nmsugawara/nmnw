package nmnw.admin.utility;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.nmnw.admin.utility.HtmlHelper;

public class HtmlHelperTest {

	@Test
	public void nl2brTest() {
		/**
		 * �ϊ��L�̏ꍇ
		 */
		// ������
		String expectedConverted = "test<br>test";
		String valueConverted = "test\ntest";
		// ���s
		String actualConverted = HtmlHelper.nl2br(valueConverted);
		// ����
		assertThat("nl2br:�ϊ��L", actualConverted, is(expectedConverted));
		// �㏈��

		/**
		 * �ϊ��L�̏ꍇ
		 */
		// ������
		String expectedNotConverted = "testntest";
		String valueNotConverted = "testntest";
		// ���s
		String actualNotConverted = HtmlHelper.nl2br(valueNotConverted);
		// ����
		assertThat("nl2br:�ϊ���", actualNotConverted, is(expectedNotConverted));
		// �㏈��
	}

	@Test
	public void htmlspecialcharsTest() {
		/**
		 * �ϊ��L�̏ꍇ
		 */
		// ������
		String expectedConverted = "&amp; &lt; &gt; &quot; &#39; <br> &#x0009;";
		String valueConverted = "& < > \" \' \n \t";
		// ���s
		String actualConverted = HtmlHelper.htmlspecialchars(valueConverted);
		// ����
		assertThat("htmlspecialchars:�ϊ��L", actualConverted, is(expectedConverted));
		// �㏈��

		/**
		 * �ϊ����̏ꍇ
		 */
		// ������
		String expectedNotConverted = "test";
		String valueNotConverted = "test";
		// ���s
		String actualNotConverted = HtmlHelper.htmlspecialchars(valueNotConverted);
		// ����
		assertThat("htmlspecialchars:�ϊ���", actualNotConverted, is(expectedNotConverted));
		// �㏈��
	}
}
