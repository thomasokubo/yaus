package com.tto.yaus.usecases;

import com.tto.yaus.gateways.UrlGateway;
import com.tto.yaus.usecases.addurl.AddUrlInputPort;
import com.tto.yaus.usecases.addurl.AddUrlInteractor;
import com.tto.yaus.usecases.addurl.AddUrlOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddUrlInteractorTest {
    @Mock
    UrlGateway urlGateway;

    @Mock
    AddUrlOutputPort presenter;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @Test
    public void givenUrlThenSaveHashWithProperLength() {
        String url = "https://mywebsite.com.br";

        AddUrlInputPort addUrlInteractor = new AddUrlInteractor(urlGateway, presenter);

        addUrlInteractor.execute(url);

        verify(urlGateway, times(1)).save(argumentCaptor.capture(), eq(url));
        verify(presenter, times(1)).accept(argumentCaptor.getValue());

        assertEquals(8, argumentCaptor.getValue().length());
    }
}
