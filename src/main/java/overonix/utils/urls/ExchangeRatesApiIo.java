package overonix.utils.urls;

public class ExchangeRatesApiIo {
    public static final String APIKEY = "ee6669a1d673f093cd4e3f7113b71ead";
    public static final String URL = "http://api.exchangeratesapi.io/v1/latest?access_key="
            + APIKEY;
    public static final String URL_WITH_BASE = "http://api.exchangeratesapi.io/v1/latest?"
            + "access_key=" + APIKEY + "&base=USA";
}
