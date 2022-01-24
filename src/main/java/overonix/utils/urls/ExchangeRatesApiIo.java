package overonix.utils.urls;

public class ExchangeRatesApiIo {
    public static final String APIKEY = "ee6669a1d673f093cd4e3f7113b71ead";
    public static final String URL = "http://api.exchangeratesapi.io/v1/latest?access_key="
            + APIKEY;
    public static final String URL_WITH_BASE = "http://api.exchangeratesapi.io/v1/latest?"
            + "access_key=" + APIKEY + "&base=USA";

    public static final String EXAMPLE = ""
            + "{\"success\":true,"
            + "\"timestamp\":1642783457,"
            + "\"base\":\"EUR\","
            + "\"date\":\"2022-01-21\","
            + "\"rates\":{"
            + "\"AED\":4.166206,\"AFN\":119.217567,\"ALL\":121.650391,\"AMD\":546.853389,"
            + "\"ANG\":2.044738,\"AOA\":601.048077,\"ARS\":118.371372,\"AUD\":1.576063,"
            + "\"AWG\":2.036013,\"AZN\":1.907281,\"BAM\":1.958548,\"BBD\":2.290779,"
            + "\"BDT\":97.504874,\"BGN\":1.952613,\"BHD\":0.427577,\"BIF\":2281.015171,"
            + "\"BMD\":1.134269,\"BND\":1.526632,\"BOB\":7.822756,\"BRL\":6.154094,"
            + "\"BSD\":1.134575,\"BTC\":2.93252e-5,\"BTN\":84.40558,\"BWP\":13.034005,"
            + "\"BYN\":2.919251,\"BYR\":22231.674465,\"BZD\":2.286974,\"CAD\":1.422425,"
            + "\"CDF\":2286.687168,\"CHF\":1.034499,\"CLF\":0.032762,\"CLP\":904.057747,"
            + "\"CNY\":7.189907,\"COP\":4492.613072,\"CRC\":720.139388,\"CUC\":1.134269,"
            + " \"CUP\":30.058131,\"ZWL\":365.234189}}";
}
