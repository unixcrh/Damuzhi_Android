/**  
        * @title CommonTravelPreprationActivity.java  
        * @package com.damuzhi.travel.activity.overview  
        * @description   
        * @author liuxiaokun  
        * @update 2012-6-14 下午12:07:58  
        * @version V1.0  
 */
package com.damuzhi.travel.activity.overview;

import android.app.Activity;

import com.damuzhi.travel.R;
import com.damuzhi.travel.mission.OverviewMission;
import com.damuzhi.travel.model.app.AppManager;
import com.damuzhi.travel.model.constant.ConstantField;
import com.damuzhi.travel.protos.CityOverviewProtos.CommonOverview;
import com.damuzhi.travel.protos.CityOverviewProtos.CommonOverviewType;

/**  
 * @description   
 * @version 1.0  
 * @author liuxiaokun  
 * @update 2012-6-14 下午12:07:58  
 */

public class CommonTravelPreprationActivity extends CommonOverViewActivity
{

	@Override
	public CommonOverview loadData(Activity activity)
	{
		return OverviewMission.getInstance().getOverview(CommonOverviewType.TRAVEL_PREPRATION_VALUE,AppManager.getInstance().getCurrentCityId(),activity);
	}

	@Override
	public boolean isSupportViewpager()
	{
		return false;
	}

	@Override
	public String setTitleName()
	{
		return getString(R.string.travel_prepration);
	}

}
