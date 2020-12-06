package org.magi.module.esimdb.page;

import org.magi.Constans;
import org.magi.module.commons.model.CarrierModel;
import org.magi.module.commons.model.SpiderPlan;
import org.magi.module.commons.model.CarrierPlanModel;
import org.magi.module.commons.page.BasePageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jonah
 */
public class CarrierPageProcessor extends BasePageProcessor {


    Pattern pattern = Pattern.compile("esimdb\\.com/(?<country>(\\w+)?)/(?<carrier>(\\w+)?)");


    private CarrierModel getCarrierModel(Page page) {
        String url = page.getRequest().getUrl();
        Matcher matcher = pattern.matcher(url);
        String country = null;
        String imgSuffer = page.getHtml().css(".provider-thumb>img", "src").get();
        String img = Constans.ESIM_DB_BASE_URL + imgSuffer;
        String carrierUrl = page.getHtml().css(".provider-links").links().get();
        String carrierName = page.getHtml().css(".page-header-title").links().get();
        if (matcher.find()) {
            country = matcher.group("country");
            carrierName = matcher.group("carrier");
        }
        String desc = page.getHtml().css(".ts-lead>p", "allText").get();
        return CarrierModel.builder()
                       .imgUrl(img)
                       .desc(desc)
                       .country(country)
                       .name(carrierName)
                       .url(carrierUrl)
                       .build();
    }


    @Override
    public void process(Page page) {
        CarrierModel carrier = getCarrierModel(page);
        List<CarrierPlanModel> plans = getCarrierPlanModels(page);
        SpiderPlan pageModel = SpiderPlan.builder().carrier(carrier).plans(plans).build();
        page.putField(CarrierPipeline.MODELS, pageModel);
    }

    private List<CarrierPlanModel> getCarrierPlanModels(Page page) {
        // 处理 card 套餐数据
        List<Selectable> cardContent = page.getHtml().css(".card-content").nodes();
        List<CarrierPlanModel> models = new ArrayList<>();
        for (Selectable selectable : cardContent) {
            String title = selectable.css(".card-title", "allText").get();
            String price = selectable.css(".price", "allText").get();
            String volume = selectable.css(".size-period>strong", "allText").get();
            String additionalInfo = selectable.css(".size-period>.additional-info", "allText").get();
            // TODO 覆盖地区 之后做
            CarrierPlanModel model = CarrierPlanModel.builder()
                                             .title(title)
                                             .price(price)
                                             .additionalInfo(additionalInfo)
                                             .volume(volume)
                                             .build();
            models.add(model);
        }
        return models;
    }


}
