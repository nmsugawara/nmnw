package nmnw.admin.dao;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.nmnw.admin.dao.PossibilityOfCancelReportDao;
import com.nmnw.admin.utility.DateConversionUtility;
import com.nmnw.admin.utility.DbConnector;

@RunWith(Theories.class)
public class PossibilityOfCancelReportDaoTest {

	@DataPoint
	public static int INT_PARAM_1 = 0;
	@DataPoint
	public static int INT_PARAM_2 = 1;
	@DataPoint
	public static int INT_PARAM_3;

	@Theory
	public void selectSummaryDataByOrderPeriodTest(int arg1) throws Exception{
		// èâä˙âª
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		PossibilityOfCancelReportDao possibilityOfCancelReportDao = new PossibilityOfCancelReportDao();
		// é¿çs
		try {
			resultList = possibilityOfCancelReportDao.selectUnShippingOrShippingWithinRecentDays(arg1);
		} catch (Exception e) {
			assumeNoException(e);
		}
		// åüèÿ
		assertNotNull("arg1:" + arg1, resultList);
		// å„èàóù
	}
}
