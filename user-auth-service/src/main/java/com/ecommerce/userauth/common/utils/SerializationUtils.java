package com.ecommerce.userauth.common.utils;
import com.ecommerce.userauth.domain.common.CurrentUserContext;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Base64;


@UtilityClass
public class SerializationUtils {

    public static <T> String serialize(T objectToBeSerialized) {
        byte[] byteArray = toByteStream(objectToBeSerialized);
        return toBase64(byteArray);
    }

    public static <T> T deserialize(String base64String, Class<T> clazz) {
        byte[] byteArray = toByteArray(base64String);
        return toObject(byteArray, clazz);
    }

    public static <T> byte[] toByteStream(T objectToBeSerialized) {

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
            out.writeObject(objectToBeSerialized);
            out.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return new byte[0];
    }

    public static <T> T toObject(byte[] byteArray, Class<T> clazz) {

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(bais);
            T obj = clazz.cast(ois.readObject());
            ois.close();
            return obj;
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String toBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }

    public static byte[] toByteArray(String base64String) {
        return Base64.getUrlDecoder().decode(base64String);
    }

    public static String toByteArrayToString(String base64String) {
        return new String(Base64.getUrlDecoder().decode(base64String));
    }

    public static CurrentUserContext getContextObject(Object deserializedObject) {
        CurrentUserContext currentUserContext = new CurrentUserContext();
        try {

            Field userIdentityField = deserializedObject.getClass().getDeclaredField("userIdentity");
            userIdentityField.setAccessible(true);
            String userIdentity = (String) userIdentityField.get(deserializedObject);

            Field userTypeField = deserializedObject.getClass().getDeclaredField("userType");
            userTypeField.setAccessible(true);
            String userType = (String) userTypeField.get(deserializedObject);

            Field userStatusField = deserializedObject.getClass().getDeclaredField("userStatus");
            userStatusField.setAccessible(true);
            Integer userStatus = (Integer) userStatusField.get(deserializedObject);

            Field userLevelField = deserializedObject.getClass().getDeclaredField("userLevel");
            userLevelField.setAccessible(true);
            Integer userLevel = (Integer) userLevelField.get(deserializedObject);

            Field phoneNoField = deserializedObject.getClass().getDeclaredField("userPhoneNo");
            phoneNoField.setAccessible(true);
            String phoneNo = (String) phoneNoField.get(deserializedObject);

            Field clientIdField = deserializedObject.getClass().getDeclaredField("userCbsClientId");
            phoneNoField.setAccessible(true);
            String clientId = (String) clientIdField.get(deserializedObject);

            currentUserContext.setUserIdentity(userIdentity);
            currentUserContext.setUserLevel(userLevel);
            currentUserContext.setUserStatus(userStatus);
            currentUserContext.setUserPhoneNo(phoneNo);
            currentUserContext.setUserType(userType);
            currentUserContext.setUserCbsClientId(clientId);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return currentUserContext;
    }
}
