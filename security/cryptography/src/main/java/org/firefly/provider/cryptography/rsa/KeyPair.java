package org.firefly.provider.cryptography.rsa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class KeyPair<P, V> {
    private P publicKey;
    private V privateKey;
}
