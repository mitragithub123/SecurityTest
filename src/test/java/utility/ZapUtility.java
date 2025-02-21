package utility;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Proxy;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ApiResponseList;

public class ZapUtility {

	private static ClientApi clientApi;
	public static Proxy proxy;
	private static ApiResponse apiResponse;

	private static final String zapAddress = "127.0.0.1";
	private static final int zapPort = 8080;
	private static final String apiKey = "13o486bqflpks3lfp5ke6a7qor";

	static {
		clientApi = new ClientApi(zapAddress, zapPort, apiKey);
		proxy = new Proxy().setSslProxy(zapAddress + ":" + zapPort).setHttpProxy(zapAddress + ":" + zapPort);
	}

	public static void waitTillPassiveScanCompleted() {
		try {
			apiResponse = clientApi.pscan.recordsToScan();
			String tempVal = ((ApiResponseElement) apiResponse).getValue();
			while (!tempVal.equals("0")) {
				System.out.println("passive scan is in progress");
				apiResponse = clientApi.pscan.recordsToScan();
				tempVal = ((ApiResponseElement) apiResponse).getValue();
			}
			System.out.println("passive scan is completed");
		} catch (ClientApiException e) {
			e.printStackTrace();
		}
	}
	
	 public static void addURLToScanTree(String siteToTest) throws ClientApiException {
	        clientApi.core.accessUrl(siteToTest, "false");
	        if(getUrlsFromScanTree().contains(siteToTest))
	            System.out.println(siteToTest+ " has been added to scan tree");
	        else
	            throw new RuntimeException(siteToTest +" not added to scan tree, active scan will not be possible");
	    }

	    public static List<String> getUrlsFromScanTree() throws ClientApiException {
	        apiResponse=clientApi.core.urls();
	        List<ApiResponse> responses=((ApiResponseList)apiResponse).getItems();
	        return responses.stream().map(r->((ApiResponseElement)r).getValue()).collect(Collectors.toList());
	    }

	    public static void performActiveScan(String siteToTest) throws ClientApiException {
	        String url = siteToTest;
	        String recurse = null;
	        String inscopeonly = null;
	        String scanpolicyname = null;
	        String method = null;
	        String postdata = null;
	        Integer contextId= null;
	        apiResponse = clientApi.ascan.scan(url, recurse, inscopeonly, scanpolicyname, method, postdata, contextId);
	        String scanId = ((ApiResponseElement) apiResponse).getValue();

	        waitTillActiveScanIsCompleted(scanId);
	    }

	    private static void waitTillActiveScanIsCompleted(String scanId) throws ClientApiException {
	        apiResponse=clientApi.ascan.status(scanId);
	        String status=((ApiResponseElement)apiResponse).getValue();

	        while (!status.equals("100")){
	            apiResponse=clientApi.ascan.status(scanId);
	            status=((ApiResponseElement)apiResponse).getValue();
	            System.out.println("Active scan is in progress");
	        }

	        System.out.println("Active scan has completed");
	    }
	
	
	// Generate traditional plus html report
	public static void generateZapReport(String siteToTest) {
		String title = "BeePlugin Test";
		String template = "traditional-html-plus";
		String theme = "light";
		String description = "Demo description";
		String contexts = null;
		String sections = null;
		String includedconfidences = null;
		String includedrisks = "High|Medium|Low";
		String reportfilename = null;
		String reportfilenamepattern = "{{yyyy-MM-dd}}-ZAP-Report-[[site]]";
		String reportdir = System.getProperty("user.dir") + "/reports";
		String display = "true";

		try {
			clientApi.reports.generate(title, template, theme, description, contexts, siteToTest, sections,
					includedconfidences, includedrisks, reportfilename, reportfilenamepattern, reportdir, display);
		} catch (ClientApiException e) {
			e.printStackTrace();
		}
	}
	
	// Generate traditional html report
	 /*public static void generateZapReport(String siteToTest) {
	        String title = "Demo Title";
	        String template = "traditional-html";
	        String theme = null;
	        String description = "Demo description";
	        String contexts = null;
	        String sites = siteToTest;
	        String sections = null;
	        String includedconfidences = null;
	        String includedrisks = null;
	        String reportfilename = "Demofilename";
	        String reportfilenamepattern = null;
	        String reportdir = System.getProperty("user.dir");
	        String display = null;

	        try {
	            clientApi.reports.generate(title, template, theme, description, contexts, sites, sections,
	                    includedconfidences, includedrisks, reportfilename, reportfilenamepattern, reportdir, display);
	        } catch (ClientApiException e) {
	            e.printStackTrace();
	        }
	    }*/
}
