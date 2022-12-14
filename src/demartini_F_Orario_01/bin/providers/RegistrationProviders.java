package demartini_F_Orario_01.bin.providers;

import demartini_F_Orario_01.bin.PacketErrorCode;
import demartini_F_Orario_01.bin.Utility;
import demartini_F_Orario_01.bin.packages.MTPError;
import demartini_F_Orario_01.bin.packages.MTPPacket;
import demartini_F_Orario_01.bin.packages.MTPRegistrationRequest;
import demartini_F_Orario_01.bin.packages.MTPRegistrationSuccess;

public class RegistrationProviders {

    public static MTPPacket evaluateRequest(
            MTPRegistrationRequest registrationRequest
    ) {
        if (registrationRequest.getName().equals("deMartini")) {
            return new MTPRegistrationSuccess(Utility.nextNonNegative());
        }
        return new MTPError(PacketErrorCode.REGISTRATION_ERROR);
    }
}
