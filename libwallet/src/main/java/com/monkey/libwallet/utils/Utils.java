package com.monkey.libwallet.utils;

import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

import java.nio.charset.Charset;

/**
 * 作者：created by albert on 2019-10-08 11:32
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class Utils {

    private static final int SEED_ITERATIONS = 2048;
    private static final int SEED_KEY_SIZE = 512;

    public static byte[] generateSeed(String mnemonic, String passphrase) {
        validateMnemonic(mnemonic);
        passphrase = passphrase == null ? "" : passphrase;


        String salt = String.format("mnemonic%s", passphrase);
        PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator(new SHA512Digest());
        gen.init(mnemonic.getBytes(Charset.forName("UTF-8")), salt.getBytes(Charset.forName("UTF-8")), SEED_ITERATIONS);

        return ((KeyParameter) gen.generateDerivedParameters(SEED_KEY_SIZE)).getKey();
    }

    private static void validateMnemonic(String mnemonic) {
        if (mnemonic == null || mnemonic.trim().isEmpty()) {
            throw new IllegalArgumentException("Mnemonic is required to generate a seed");
        }
    }


    public static Credentials loadCredentials(String password, WalletFile walletFile)
            throws CipherException {
        return Credentials.create(Wallet.decrypt(password, walletFile));
    }
}
