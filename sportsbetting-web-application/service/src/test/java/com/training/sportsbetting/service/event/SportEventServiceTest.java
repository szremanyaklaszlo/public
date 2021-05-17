package com.training.sportsbetting.service.event;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.sportsbetting.service.event.exception.SportEventNotFoundException;

@ExtendWith(MockitoExtension.class)
public class SportEventServiceTest {

    @Mock
    private SportEventRepository sportEventRepository;
    @InjectMocks
    private SportEventService underTest;

    @Test
    public void findByIdShouldThrowNullpointerExceprionWhenIdIsNull() {
        // Given
        Long id = null;
        // When
        // Then
        assertThrows(NullPointerException.class, () -> underTest.findById(id));
    }

    @Test
    public void findByIdShouldThrowSportEventNotFoundExceptionWhenNoSportEventWithId() {
        // Given
        Long id = 1L;
        // When
        when(sportEventRepository.findById(id)).thenReturn(Optional.empty());
        // Then
        assertThrows(SportEventNotFoundException.class, () -> underTest.findById(id));
    }

}
