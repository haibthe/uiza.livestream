package uizalivestream.util;

import android.content.Context;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import vn.uiza.core.common.Constants;
import vn.uiza.restapi.restclient.UZRestClient;
import vn.uiza.utils.util.Utils;

import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ UZRestClient.class, Utils.class })
public class UZUtilTest {

    private String domainApi = "domain";
    private String token = "token";
    private String appId = "appId";
    private final int API_VERSION_3 = 3;

    @Before
    public void setup() {
        // Mock the static instances
        PowerMockito.mockStatic(UZRestClient.class, Utils.class);
    }

    @Test
    public void initWorkspace_correct() {
        // Mock the context
        Context context = Mockito.mock(Context.class);
        Mockito.when(context.getApplicationContext()).thenReturn(context);

        // Call the method need to test
        UZUtil.initWorkspace(context, API_VERSION_3, domainApi, token, appId);

        // To verify these below static methods are called if all params are correct
        PowerMockito.verifyStatic(Utils.class, times(1));
        Utils.init(context);
        PowerMockito.verifyStatic(UZRestClient.class, times(1));
        UZRestClient.init(Constants.PREFIXS + domainApi, token);
    }

    @Test(expected = NullPointerException.class)
    public void initWorkspace_withNullContext_error() {
        // Call the method need to test
        UZUtil.initWorkspace(null, API_VERSION_3, domainApi, token, appId);
    }

    @Test(expected = NullPointerException.class)
    public void initWorkspace_withNullDomain_error() {
        // Mock the context
        Context context = Mockito.mock(Context.class);
        Mockito.when(context.getApplicationContext()).thenReturn(context);

        // Call the method need to test
        UZUtil.initWorkspace(context, API_VERSION_3, null, token, appId);
    }

    @Test(expected = NullPointerException.class)
    public void initWorkspace_withNullToken_error() {
        // Mock the context
        Context context = Mockito.mock(Context.class);
        Mockito.when(context.getApplicationContext()).thenReturn(context);

        // Call the method need to test
        UZUtil.initWorkspace(context, API_VERSION_3, domainApi, null, appId);
    }

    @Test(expected = NullPointerException.class)
    public void initWorkspace_withNullAppId_error() {
        // Mock the context
        Context context = Mockito.mock(Context.class);
        Mockito.when(context.getApplicationContext()).thenReturn(context);

        // Call the method need to test
        UZUtil.initWorkspace(context, API_VERSION_3, domainApi, token, null);
    }
}
