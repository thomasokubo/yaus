package com.tto.yaus.usecases;

import com.tto.yaus.gateways.UrlGateway;
import com.tto.yaus.usecases.geturl.GetUrlInputPort;
import com.tto.yaus.usecases.geturl.GetUrlInteractor;
import com.tto.yaus.usecases.geturl.GetUrlOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUrlnteractorTest {
    @Mock
    private UrlGateway urlGateway;

    @Mock
    private GetUrlOutputPort presenter;

    @Test
    public void givenValidHashThenGetUrl() {
        String hash = "hash";
        String url = "https://mywebsite.com.br";
        GetUrlInputPort useCase = new GetUrlInteractor(urlGateway, presenter);

        when(urlGateway.findByHash(anyString())).thenReturn(url);

        useCase.execute(hash);

        verify(urlGateway, times(1)).findByHash(hash);
        verify(presenter, times(1)).accepts(url);
    }
}
