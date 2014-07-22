package nmnw.admin.dao;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.nmnw.admin.dao.MonthlySummaryReportDao;
import com.nmnw.admin.utility.DateConversionUtility;
import com.nmnw.admin.utility.DbConnector;

@RunWith(Theories.class)
public class MonthlySummaryReportDaoTest {

	@DataPoint
	public static String STRING_PARAM_1 = "2014-04-01";
	@DataPoint
	public static String STRING_PARAM_2 = "2014-07-01";
	@DataPoint
	public static String STRING_PARAM_3 = null;
	@DataPoint
	public static String STRING_PARAM_4 = "";

	@Theory
	public void selectSummaryDataByOrderPeriodTest(String arg1, String arg2) throws Exception{
		// èâä˙âª
		Map<String, String> resultList = new HashMap<String, String>();
		MonthlySummaryReportDao monthlySummaryReportDao = new MonthlySummaryReportDao();
		// é¿çs
		try {
			resultList = monthlySummaryReportDao.selectSummaryDataByOrderPeriod(arg1, arg2);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// åüèÿ
		assertNotNull("arg1:" + arg1 + ", arg2:" + arg2, resultList);
		// å„èàóù
	}
}
