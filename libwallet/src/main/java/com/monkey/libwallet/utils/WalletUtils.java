package com.monkey.libwallet.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.monkey.libwallet.model.Wallet;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.HDUtils;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletFile;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import io.github.novacrypto.bip39.MnemonicGenerator;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;

import static com.monkey.libwallet.utils.Utils.loadCredentials;
import static org.web3j.crypto.MnemonicUtils.generateSeed;

/**
 * 作者：created by albert on 2019-10-08 11:21
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class WalletUtils {

    /**
     * 创建钱包
     * @param walletName
     * @param pin
     * @return
     * @throws CipherException
     */
    public static Wallet CreateWallet(String walletName,String pin) throws CipherException {

        StringBuilder sb = new StringBuilder();
        byte[] entropy = new byte[Words.TWELVE.byteLength()];
        new SecureRandom().nextBytes(entropy);
        new MnemonicGenerator(English.INSTANCE).createMnemonic(entropy, sb::append);
        String mnemonics = sb.toString();
        byte[] seed = generateSeed(mnemonics, null);
        DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy dh = new DeterministicHierarchy(rootPrivateKey);
        List<ChildNumber> parentPath = HDUtils.parsePath("M/44H/60H/0H/0");
        DeterministicKey child = dh.deriveChild(parentPath, true, true, new ChildNumber(0));
        byte[] privateKeyByte = child.getPrivKeyBytes();
        ECKeyPair keyPair = ECKeyPair.create(privateKeyByte);
        WalletFile walletFile = org.web3j.crypto.Wallet.createLight(pin, keyPair);
        String address = Keys.toChecksumAddress(walletFile.getAddress());
        Gson gson = new Gson();
        String keystore = gson.toJson(walletFile);

        Wallet wallet = new Wallet();
        wallet.setAddress(address);
        wallet.setKeystore(keystore);
        wallet.setMnemonic(mnemonics);
        wallet.setPin(pin);
        wallet.setPrikey(Numeric.toHexStringNoPrefix(keyPair.getPrivateKey()));
        wallet.setPubkey(Numeric.toHexStringNoPrefix(keyPair.getPublicKey()));
        wallet.setWalletName(walletName);

        return wallet;
    }

    /**
     * Keystory导入钱包
     * @param walletName
     * @param keystory
     * @param pin
     * @return
     * @throws Exception
     */
    public static Wallet ImportKeystoryWallet(String walletName,String keystory,String pin) throws Exception{

        ObjectMapper om = new ObjectMapper();
        WalletFile walletFile = om.readValue(keystory, WalletFile.class);
        Credentials c = loadCredentials(pin, walletFile);
        String publicKey = Numeric.toHexStringNoPrefix(c.getEcKeyPair().getPublicKey());
        String privateKey = Numeric.toHexStringNoPrefix(c.getEcKeyPair().getPrivateKey());
        String address = Keys.toChecksumAddress(walletFile.getAddress());

        Gson gson = new Gson();
        String keystore = gson.toJson(walletFile);

        Wallet wallet = new Wallet();
        wallet.setAddress(address);
        wallet.setKeystore(keystore);
        wallet.setPin(pin);
        wallet.setPrikey(privateKey);
        wallet.setPubkey(publicKey);
        wallet.setWalletName(walletName);

        return wallet;
    }

    /**
     * 私钥导入钱包
     * @param walletName
     * @param prikey
     * @param pin
     * @return
     * @throws Exception
     */
    public static Wallet ImportPrikeyWallet(String walletName,String prikey ,String pin) throws Exception{

        BigInteger pk = Numeric.toBigInt(prikey);

        byte[] privateKeyByte = pk.toByteArray();

        ECKeyPair keyPair = ECKeyPair.create(privateKeyByte);
        WalletFile walletFile = org.web3j.crypto.Wallet.createLight(pin, keyPair);
        String address = Keys.toChecksumAddress(walletFile.getAddress());

        Gson gson = new Gson();
        String keystore = gson.toJson(walletFile);

        Wallet wallet = new Wallet();
        wallet.setAddress(address);
        wallet.setKeystore(keystore);
        wallet.setPin(pin);
        wallet.setPrikey(Numeric.toHexStringNoPrefix(keyPair.getPrivateKey()));
        wallet.setPubkey(Numeric.toHexStringNoPrefix(keyPair.getPublicKey()));
        wallet.setWalletName(walletName);

        return wallet;
    }

    /**
     * 助记词导入钱包
     * @param walletName
     * @param mnemonics
     * @param pin
     * @return
     * @throws Exception
     */
    public static Wallet ImportMnemonicsWallet(String walletName,String mnemonics,String pin) throws Exception{

        byte[] seed = generateSeed(mnemonics, null);
        DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy dh = new DeterministicHierarchy(rootPrivateKey);
        List<ChildNumber> parentPath = HDUtils.parsePath("M/44H/60H/0H/0");
        DeterministicKey child = dh.deriveChild(parentPath, true, true, new ChildNumber(0));
        byte[] privateKeyByte = child.getPrivKeyBytes();

        ECKeyPair keyPair = ECKeyPair.create(privateKeyByte);
        WalletFile walletFile = org.web3j.crypto.Wallet.createLight(pin, keyPair);
        String address = Keys.toChecksumAddress(walletFile.getAddress());

        Gson gson = new Gson();
        String keystore = gson.toJson(walletFile);

        Wallet wallet = new Wallet();
        wallet.setAddress(address);
        wallet.setKeystore(keystore);
        wallet.setMnemonic(mnemonics);
        wallet.setPin(pin);
        wallet.setPrikey(Numeric.toHexStringNoPrefix(keyPair.getPrivateKey()));
        wallet.setPubkey(Numeric.toHexStringNoPrefix(keyPair.getPublicKey()));
        wallet.setWalletName(walletName);
        return wallet;
    }

}
