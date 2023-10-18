package org.example.mySubTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;

public class YooMoneyApi {
    private  CloseableHttpClient httpClient;
    HttpPost httpPost;
    String requestBody = ""; //пока что значение по умолчанию
    double amount;
    String token = "Bearer 410012345678901.0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123";
    public YooMoneyApi(){

        httpClient = HttpClients.createDefault();
        // Создание объекта POST-запроса
        httpPost = new HttpPost("https://yoomoney.ru/api/request-payment");
        // Установка заголовков
        httpPost.setHeader("Authorization", token);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Content-Length", "234");
    }

    public void pay(
            String pattern_id,
            double amount,
            String recipient,
            String message
    ) throws UnsupportedEncodingException {
        requestBody = "pattern_id="+pattern_id
                        +"&to="+recipient
                        +"&amount="+amount
                        +"&message="+message
                        +"comment=%D0%A1%D0%BE%D0%BE%D0%B1%D1%89%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%BF%D0%BE%D0%BB%D1%83%D1%87%D0%B0%D1%82%D0%B5%D0%BB%D1%8E";
        httpPost.setEntity(new StringEntity(requestBody));

        // Проверка на успешный статус код (200 - OK)
        try {
            // Выполнение запроса и получение ответа
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            // Проверка на успешный статус код
            if (response.getStatusLine().getStatusCode() == 200) {
                // Получение JSON-ответа
                String jsonResponse = EntityUtils.toString(entity);
                System.out.println(jsonResponse);
            } else {
                System.out.println("Ошибка в запросе: " + response.getStatusLine());
            }

            // Закрытие ресурсов
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
