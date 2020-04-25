package spi;

public class SPIServiceImpl implements SPIService {
    @Override
    public void execute() {
        System.out.println("SPIServiceImpl执行");
    }
}
