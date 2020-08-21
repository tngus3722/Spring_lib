package slack;

import domain.SlackAttachment;
import domain.SlackParameter;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;

@Service
public class SlackSender {
    private String url = "https://hooks.slack.com/services/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";
    private RestTemplate restTemplate;
    private SlackParameter slackParameter;
    private HttpHeaders headers;
    public SlackSender(){
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
                " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter((Charset.forName("UTF-8"))));
        slackParameter = new SlackParameter();
        slackParameter.setChannel("#tngus");
        slackParameter.setUsername("알람");
    };

    public void noticePost(SlackAttachment slackAttachment){
        slackParameter.setText("noticePost");

        ArrayList<SlackAttachment> list = new ArrayList<SlackAttachment>();
        list.add(slackAttachment);

        slackParameter.setAttachments(list);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<SlackParameter> entity = new HttpEntity<SlackParameter>(slackParameter, this.headers);

        restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
    }

    public void noticeError(SlackAttachment slackAttachment){
        slackParameter.setText("noticeError");

        ArrayList<SlackAttachment> list = new ArrayList<SlackAttachment>();
        list.add(slackAttachment);

        slackParameter.setAttachments(list);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<SlackParameter> entity = new HttpEntity<SlackParameter>(slackParameter, this.headers);

        restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
    }
}
