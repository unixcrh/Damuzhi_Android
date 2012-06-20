/**  
        * @title CommonTravelTransportationActivity.java  
        * @package com.damuzhi.travel.activity.overview  
        * @description   
        * @author liuxiaokun  
        * @update 2012-6-14 下午12:32:00  
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
 * @update 2012-6-14 下午12:32:00  
 */

public class CommonTravelTransportationActivity extends CommonOverViewActivity
{

	@Override
	public CommonOverview loadData(Activity activity)
	{
		return OverviewMission.getInstance().getOverview(CommonOverviewType.TRAVEL_TRANSPORTATION_VALUE,AppManager.getInstance().getCurrentCityId(),activity);

	}

	@Override
	public boolean isSupportViewpager()
	{
		return false;
	}

	@Override
	public String setTitleName()
	{
		return getString(R.string.travel_transportaion);
	}

}
