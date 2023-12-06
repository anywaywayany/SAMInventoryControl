package com.samic.samic.data.foundation;

import com.samic.samic.exceptions.SamicException;
import org.junit.jupiter.api.Test;

import static com.samic.samic.data.foundation.Guard.ensureNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GuardTest{

    @Test
    void ensureNotNullWithNullThrowsException(){
        var ex = assertThrows(SamicException.class, ()-> ensureNotNull(null));
        assertThat(ex).hasMessageContaining("'argument' must not be null!");
    }

    @Test
    void ensureNotNullThrowsException(){
        var ex = assertThrows(SamicException.class,()-> ensureNotNull(null, "aaa"));
        assertThat(ex).hasMessageContaining("'aaa' must not be null!");
    }

    @Test
    void ensureNotNullWithNonNullArgReturnsArg(){
        assertThat(ensureNotNull("A")).isEqualTo("A");
    }

}