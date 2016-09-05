package com.example.junze.aqiconverter;

/**
 * Created by junze on 16-06-25.
 */
public class AQICalculator {

    public static double calculateAQI(double cp) {
        if (cp < 0) {
            return -1;
        } else if (cp <= 12) {
            return concentrationToAQI(cp, 12, 0, 50, 0);
        } else if (cp <= 35.4) {
            return concentrationToAQI(cp, 35.4, 12.1, 100, 51);
        } else if (cp <= 55.4) {
            return concentrationToAQI(cp, 55.4, 35.5, 150, 101);
        } else if (cp <= 150.4) {
            return concentrationToAQI(cp, 150.4, 55.5, 200, 151);
        } else if (cp <= 250.4) {
            return concentrationToAQI(cp, 250.4, 150.5, 300, 201);
        } else if (cp <= 350.4) {
            return concentrationToAQI(cp, 350.4, 250.5, 400, 301);
        } else if (cp <= 500){
            return concentrationToAQI(cp, 500, 350.5, 500, 401);
        } else {
            return 500;
        }
    }
    public static double calculateConcentration(double ip) {
        if (ip < 0) {
            return -1;
        } else if (ip <= 50) {
            return AQItoConcentration(ip,0,12,0,50);
        } else if (ip <= 100) {
            return AQItoConcentration(ip,12.1,35.4,51,100);
        } else if (ip <= 150) {
            return AQItoConcentration(ip,35.5,55.4,101,150);
        } else if (ip <= 200) {
            return AQItoConcentration(ip,55.5,150.4,151,200);
        } else if (ip <= 300) {
            return AQItoConcentration(ip,150.5,250.4,201,300);
        } else if (ip <= 400) {
            return AQItoConcentration(ip,250.5,350.4,301,400);
        } else if (ip <= 500) {
            return AQItoConcentration(ip,350.5,500,401,500);
        } else {
            return 500;
        }
    }
    public static double concentrationToAQI(double cp, double bphi, double bplo, double ihi, double ilo) {
        return Math.round((ihi - ilo) / (bphi - bplo) * (cp- bplo) + ilo);
    }
    public static double AQItoConcentration(double ip, double bplo, double bphi, double ilo, double ihi) {
        return Math.round((ip-ilo) * (bphi-bplo) / (ihi-ilo) + bplo);
    }
}