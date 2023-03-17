package Chart;

import org.knowm.xchart.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("chart")
public class Chart {
    public CategoryChart drawChart(List<Double> list,String title,double b,double miu)
    {

        CategoryChart chart = new CategoryChartBuilder()
                .width(1200)
                .height(800)
                .title(title)
                .xAxisTitle("")
                .yAxisTitle("频数")
                .build();

        // 添加数据源
        Histogram histogram = new Histogram(list, 200);
        chart.addSeries("histogram", histogram.getxAxisData(), histogram.getyAxisData());
        return chart;
    }

    public void showChart(CategoryChart chart)
    {
        new SwingWrapper<CategoryChart>(chart).displayChart();
    }

}
