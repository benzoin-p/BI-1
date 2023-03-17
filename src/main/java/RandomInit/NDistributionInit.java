package RandomInit;

import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;

@Component("nd")
public class NDistributionInit {
    public void initND(double b, double miu, int num, List<Double> list)
    {
        Random random = new Random();
        for(int i=0;i<num;i++)
        {
            DecimalFormat decimalFormat = new DecimalFormat("0.000");
            double d = random.nextGaussian()*Math.sqrt(b)+miu;
            double t = Math.abs(d);
            if(d<0)
            {
                if(random.nextDouble()>0.85)
                {
                    d-= (3-t)*random.nextDouble()*0.2;
                }
            }
            else
            {
                if(random.nextDouble()>0.85)
                {
                    d+= (3-t)*random.nextDouble()*0.2;
                }
            }
            list.add(Double.valueOf(decimalFormat.format(d)));
        }
    }

    public void positiveInit(double b, double miu, int num, List<Double> list)
    {
        Random random = new Random();
        for(int i=0;i<num;i++)
        {
            DecimalFormat decimalFormat = new DecimalFormat("0.000");
            list.add(Double.valueOf(decimalFormat.format(Math.exp(random.nextGaussian()*Math.sqrt(b)+miu))));
        }
    }

    public void negativeInit(double b, double miu, int num, List<Double> list)
    {
        Random random = new Random();
        double max = 0.00;
        for(int i=0;i<num;i++)
        {
            DecimalFormat decimalFormat = new DecimalFormat("0.000");
            list.add(Double.valueOf(decimalFormat.format(random.nextGaussian()*Math.sqrt(b)+miu)));
            if(list.get(i)>max)
            {
                max = list.get(i);

            }
        }
        for(int i=0;i< num;i++)
        {
            DecimalFormat decimalFormat = new DecimalFormat("0.000");
            list.add(i,Double.valueOf(decimalFormat.format(1-(Math.exp(random.nextGaussian()*Math.sqrt(b)+miu)))));
        }
    }

    public void printND(List<Double> list)
    {
        System.out.println("偏度为："+getSkewness(list));
        System.out.println("峰度为："+getKurtosis(list));
    }

    public double getSkewness(List<Double> list)
    {
        double[] a = new double[list.size()];
        Skewness skewness = new Skewness();
        for(int i=0;i< list.size();i++)
        {
            a[i]=list.get(i);
        }
        return skewness.evaluate(a);
    }

    public double getKurtosis(List<Double> list)
    {
        double[] a = new double[list.size()];
        Kurtosis kurtosis = new Kurtosis();
        for(int i=0;i< list.size();i++)
        {
            a[i]=list.get(i);
        }
        return kurtosis.evaluate(a);
    }
}
