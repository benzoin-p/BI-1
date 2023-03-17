import Chart.Chart;
import RandomInit.NDistributionInit;
import org.knowm.xchart.CategoryChart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Application.xml");
        NDistributionInit nDistributionInit = (NDistributionInit) ctx.getBean("nd");
        Chart chart = (Chart)ctx.getBean("chart");
        List<Double> list = new ArrayList<>();
        nDistributionInit.initND(1,0,100000,list);
        nDistributionInit.printND(list);
        CategoryChart categoryChart = chart.drawChart(list,"直方图",1,0);
        chart.showChart(categoryChart);

    }
}
