package com.demotest.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeFeng {
   @SerializedName ("HeWeather5")
   private final List< HeWeather5 > fHeWeather5;

   public HeFeng( List< HeWeather5 > pHeWeather5 ) {
	  this.fHeWeather5 = pHeWeather5;
   }

   public List< HeWeather5 > getHeWeather5() {
	  return fHeWeather5;
   }

   public static class HeWeather5 {
	  @SerializedName ("aqi")
	  private final Aqi fAqi;

	  @SerializedName ("basic")
	  private final Basic fBasic;

	  @SerializedName ("daily_forecast")
	  private final List< DailyForecast > fDailyForecast;

	  @SerializedName ("hourly_forecast")
	  private final List< HourlyForecast > fHourlyForecast;

	  @SerializedName ("now")
	  private final Now fNow;

	  @SerializedName ("status")
	  private final String fStatus;

	  @SerializedName ("suggestion")
	  private final Suggestion fSuggestion;

	  public HeWeather5( Aqi pAqi, Basic pBasic, List< DailyForecast > pDailyForecast,
						 List< HourlyForecast > pHourlyForecast, Now pNow, String pStatus,
						 Suggestion pSuggestion ) {
		 this.fAqi = pAqi;
		 this.fBasic = pBasic;
		 this.fDailyForecast = pDailyForecast;
		 this.fHourlyForecast = pHourlyForecast;
		 this.fNow = pNow;
		 this.fStatus = pStatus;
		 this.fSuggestion = pSuggestion;
	  }

	  public Aqi getAqi() {
		 return fAqi;
	  }

	  public Basic getBasic() {
		 return fBasic;
	  }

	  public List< DailyForecast > getDailyForecast() {
		 return fDailyForecast;
	  }

	  public List< HourlyForecast > getHourlyForecast() {
		 return fHourlyForecast;
	  }

	  public Now getNow() {
		 return fNow;
	  }

	  public String getStatus() {
		 return fStatus;
	  }

	  public Suggestion getSuggestion() {
		 return fSuggestion;
	  }

	  public static class Aqi {
		 @SerializedName ("city")
		 private final City fCity;

		 public Aqi( City pCity ) {
			this.fCity = pCity;
		 }

		 public City getCity() {
			return fCity;
		 }

		 public static class City {
			@SerializedName ("aqi")
			private final String fAqi;

			@SerializedName ("co")
			private final String fCo;

			@SerializedName ("no2")
			private final String fNo2;

			@SerializedName ("o3")
			private final String fO3;

			@SerializedName ("pm10")
			private final String fPm10;

			@SerializedName ("pm25")
			private final String fPm25;

			@SerializedName ("qlty")
			private final String fQlty;

			@SerializedName ("so2")
			private final String fSo2;

			public City( String pAqi, String pCo, String pNo2, String pO3, String pPm10,
						 String pPm25, String pQlty, String pSo2 ) {
			   this.fAqi = pAqi;
			   this.fCo = pCo;
			   this.fNo2 = pNo2;
			   this.fO3 = pO3;
			   this.fPm10 = pPm10;
			   this.fPm25 = pPm25;
			   this.fQlty = pQlty;
			   this.fSo2 = pSo2;
			}

			public String getAqi() {
			   return fAqi;
			}

			public String getCo() {
			   return fCo;
			}

			public String getNo2() {
			   return fNo2;
			}

			public String getO3() {
			   return fO3;
			}

			public String getPm10() {
			   return fPm10;
			}

			public String getPm25() {
			   return fPm25;
			}

			public String getQlty() {
			   return fQlty;
			}

			public String getSo2() {
			   return fSo2;
			}
		 }
	  }

	  public static class Basic {
		 @SerializedName ("city")
		 private final String fCity;

		 @SerializedName ("cnty")
		 private final String fCnty;

		 @SerializedName ("id")
		 private final String fId;

		 @SerializedName ("lat")
		 private final String fLat;

		 @SerializedName ("lon")
		 private final String fLon;

		 @SerializedName ("update")
		 private final Update fUpdate;

		 public Basic( String pCity, String pCnty, String pId, String pLat, String pLon,
					   Update pUpdate ) {
			this.fCity = pCity;
			this.fCnty = pCnty;
			this.fId = pId;
			this.fLat = pLat;
			this.fLon = pLon;
			this.fUpdate = pUpdate;
		 }

		 public String getCity() {
			return fCity;
		 }

		 public String getCnty() {
			return fCnty;
		 }

		 public String getId() {
			return fId;
		 }

		 public String getLat() {
			return fLat;
		 }

		 public String getLon() {
			return fLon;
		 }

		 public Update getUpdate() {
			return fUpdate;
		 }

		 public static class Update {
			@SerializedName ("loc")
			private final String fLoc;

			@SerializedName ("utc")
			private final String fUtc;

			public Update( String pLoc, String pUtc ) {
			   this.fLoc = pLoc;
			   this.fUtc = pUtc;
			}

			public String getLoc() {
			   return fLoc;
			}

			public String getUtc() {
			   return fUtc;
			}
		 }
	  }

	  public static class DailyForecast {
		 @SerializedName ("astro")
		 private final Astro fAstro;

		 @SerializedName ("cond")
		 private final Cond fCond;

		 @SerializedName ("date")
		 private final String fDate;

		 @SerializedName ("hum")
		 private final String fHum;

		 @SerializedName ("pcpn")
		 private final String fPcpn;

		 @SerializedName ("pop")
		 private final String fPop;

		 @SerializedName ("pres")
		 private final String fPres;

		 @SerializedName ("tmp")
		 private final Tmp fTmp;

		 @SerializedName ("uv")
		 private final String fUv;

		 @SerializedName ("vis")
		 private final String fVis;

		 @SerializedName ("wind")
		 private final Wind fWind;

		 public DailyForecast( Astro pAstro, Cond pCond, String pDate, String pHum, String pPcpn,
							   String pPop, String pPres, Tmp pTmp, String pUv, String pVis, Wind pWind ) {
			this.fAstro = pAstro;
			this.fCond = pCond;
			this.fDate = pDate;
			this.fHum = pHum;
			this.fPcpn = pPcpn;
			this.fPop = pPop;
			this.fPres = pPres;
			this.fTmp = pTmp;
			this.fUv = pUv;
			this.fVis = pVis;
			this.fWind = pWind;
		 }

		 public Astro getAstro() {
			return fAstro;
		 }

		 public Cond getCond() {
			return fCond;
		 }

		 public String getDate() {
			return fDate;
		 }

		 public String getHum() {
			return fHum;
		 }

		 public String getPcpn() {
			return fPcpn;
		 }

		 public String getPop() {
			return fPop;
		 }

		 public String getPres() {
			return fPres;
		 }

		 public Tmp getTmp() {
			return fTmp;
		 }

		 public String getUv() {
			return fUv;
		 }

		 public String getVis() {
			return fVis;
		 }

		 public Wind getWind() {
			return fWind;
		 }

		 public static class Astro {
			@SerializedName ("mr")
			private final String fMr;

			@SerializedName ("ms")
			private final String fMs;

			@SerializedName ("sr")
			private final String fSr;

			@SerializedName ("ss")
			private final String fSs;

			public Astro( String pMr, String pMs, String pSr, String pSs ) {
			   this.fMr = pMr;
			   this.fMs = pMs;
			   this.fSr = pSr;
			   this.fSs = pSs;
			}

			public String getMr() {
			   return fMr;
			}

			public String getMs() {
			   return fMs;
			}

			public String getSr() {
			   return fSr;
			}

			public String getSs() {
			   return fSs;
			}
		 }

		 public static class Cond {
			@SerializedName ("code_d")
			private final String fCodeD;

			@SerializedName ("code_n")
			private final String fCodeN;

			@SerializedName ("txt_d")
			private final String fTxtD;

			@SerializedName ("txt_n")
			private final String fTxtN;

			public Cond( String pCodeD, String pCodeN, String pTxtD, String pTxtN ) {
			   this.fCodeD = pCodeD;
			   this.fCodeN = pCodeN;
			   this.fTxtD = pTxtD;
			   this.fTxtN = pTxtN;
			}

			public String getCodeD() {
			   return fCodeD;
			}

			public String getCodeN() {
			   return fCodeN;
			}

			public String getTxtD() {
			   return fTxtD;
			}

			public String getTxtN() {
			   return fTxtN;
			}
		 }

		 public static class Tmp {
			@SerializedName ("max")
			private final String fMax;

			@SerializedName ("min")
			private final String fMin;

			public Tmp( String pMax, String pMin ) {
			   this.fMax = pMax;
			   this.fMin = pMin;
			}

			public String getMax() {
			   return fMax;
			}

			public String getMin() {
			   return fMin;
			}
		 }

		 public static class Wind {
			@SerializedName ("deg")
			private final String fDeg;

			@SerializedName ("dir")
			private final String fDir;

			@SerializedName ("sc")
			private final String fSc;

			@SerializedName ("spd")
			private final String fSpd;

			public Wind( String pDeg, String pDir, String pSc, String pSpd ) {
			   this.fDeg = pDeg;
			   this.fDir = pDir;
			   this.fSc = pSc;
			   this.fSpd = pSpd;
			}

			public String getDeg() {
			   return fDeg;
			}

			public String getDir() {
			   return fDir;
			}

			public String getSc() {
			   return fSc;
			}

			public String getSpd() {
			   return fSpd;
			}
		 }
	  }

	  public static class HourlyForecast {
		 @SerializedName ("cond")
		 private final Cond fCond;

		 @SerializedName ("date")
		 private final String fDate;

		 @SerializedName ("hum")
		 private final String fHum;

		 @SerializedName ("pop")
		 private final String fPop;

		 @SerializedName ("pres")
		 private final String fPres;

		 @SerializedName ("tmp")
		 private final String fTmp;

		 @SerializedName ("wind")
		 private final Wind fWind;

		 public HourlyForecast( Cond pCond, String pDate, String pHum, String pPop, String pPres,
								String pTmp, Wind pWind ) {
			this.fCond = pCond;
			this.fDate = pDate;
			this.fHum = pHum;
			this.fPop = pPop;
			this.fPres = pPres;
			this.fTmp = pTmp;
			this.fWind = pWind;
		 }

		 public Cond getCond() {
			return fCond;
		 }

		 public String getDate() {
			return fDate;
		 }

		 public String getHum() {
			return fHum;
		 }

		 public String getPop() {
			return fPop;
		 }

		 public String getPres() {
			return fPres;
		 }

		 public String getTmp() {
			return fTmp;
		 }

		 public Wind getWind() {
			return fWind;
		 }

		 public static class Cond {
			@SerializedName ("code")
			private final String fCode;

			@SerializedName ("txt")
			private final String fTxt;

			public Cond( String pCode, String pTxt ) {
			   this.fCode = pCode;
			   this.fTxt = pTxt;
			}

			public String getCode() {
			   return fCode;
			}

			public String getTxt() {
			   return fTxt;
			}
		 }

		 public static class Wind {
			@SerializedName ("deg")
			private final String fDeg;

			@SerializedName ("dir")
			private final String fDir;

			@SerializedName ("sc")
			private final String fSc;

			@SerializedName ("spd")
			private final String fSpd;

			public Wind( String pDeg, String pDir, String pSc, String pSpd ) {
			   this.fDeg = pDeg;
			   this.fDir = pDir;
			   this.fSc = pSc;
			   this.fSpd = pSpd;
			}

			public String getDeg() {
			   return fDeg;
			}

			public String getDir() {
			   return fDir;
			}

			public String getSc() {
			   return fSc;
			}

			public String getSpd() {
			   return fSpd;
			}
		 }
	  }

	  public static class Now {
		 @SerializedName ("cond")
		 private final Cond fCond;

		 @SerializedName ("fl")
		 private final String fFl;

		 @SerializedName ("hum")
		 private final String fHum;

		 @SerializedName ("pcpn")
		 private final String fPcpn;

		 @SerializedName ("pres")
		 private final String fPres;

		 @SerializedName ("tmp")
		 private final String fTmp;

		 @SerializedName ("vis")
		 private final String fVis;

		 @SerializedName ("wind")
		 private final Wind fWind;

		 public Now( Cond pCond, String pFl, String pHum, String pPcpn, String pPres, String pTmp,
					 String pVis, Wind pWind ) {
			this.fCond = pCond;
			this.fFl = pFl;
			this.fHum = pHum;
			this.fPcpn = pPcpn;
			this.fPres = pPres;
			this.fTmp = pTmp;
			this.fVis = pVis;
			this.fWind = pWind;
		 }

		 public Cond getCond() {
			return fCond;
		 }

		 public String getFl() {
			return fFl;
		 }

		 public String getHum() {
			return fHum;
		 }

		 public String getPcpn() {
			return fPcpn;
		 }

		 public String getPres() {
			return fPres;
		 }

		 public String getTmp() {
			return fTmp;
		 }

		 public String getVis() {
			return fVis;
		 }

		 public Wind getWind() {
			return fWind;
		 }

		 public static class Cond {
			@SerializedName ("code")
			private final String fCode;

			@SerializedName ("txt")
			private final String fTxt;

			public Cond( String pCode, String pTxt ) {
			   this.fCode = pCode;
			   this.fTxt = pTxt;
			}

			public String getCode() {
			   return fCode;
			}

			public String getTxt() {
			   return fTxt;
			}
		 }

		 public static class Wind {
			@SerializedName ("deg")
			private final String fDeg;

			@SerializedName ("dir")
			private final String fDir;

			@SerializedName ("sc")
			private final String fSc;

			@SerializedName ("spd")
			private final String fSpd;

			public Wind( String pDeg, String pDir, String pSc, String pSpd ) {
			   this.fDeg = pDeg;
			   this.fDir = pDir;
			   this.fSc = pSc;
			   this.fSpd = pSpd;
			}

			public String getDeg() {
			   return fDeg;
			}

			public String getDir() {
			   return fDir;
			}

			public String getSc() {
			   return fSc;
			}

			public String getSpd() {
			   return fSpd;
			}
		 }
	  }

	  public static class Suggestion {
		 @SerializedName ("air")
		 private final Air fAir;

		 @SerializedName ("comf")
		 private final Comf fComf;

		 @SerializedName ("cw")
		 private final Cw fCw;

		 @SerializedName ("drsg")
		 private final Drsg fDrsg;

		 @SerializedName ("flu")
		 private final Flu fFlu;

		 @SerializedName ("sport")
		 private final Sport fSport;

		 @SerializedName ("trav")
		 private final Trav fTrav;

		 @SerializedName ("uv")
		 private final Uv fUv;

		 public Suggestion( Air pAir, Comf pComf, Cw pCw, Drsg pDrsg, Flu pFlu, Sport pSport,
							Trav pTrav, Uv pUv ) {
			this.fAir = pAir;
			this.fComf = pComf;
			this.fCw = pCw;
			this.fDrsg = pDrsg;
			this.fFlu = pFlu;
			this.fSport = pSport;
			this.fTrav = pTrav;
			this.fUv = pUv;
		 }

		 public Air getAir() {
			return fAir;
		 }

		 public Comf getComf() {
			return fComf;
		 }

		 public Cw getCw() {
			return fCw;
		 }

		 public Drsg getDrsg() {
			return fDrsg;
		 }

		 public Flu getFlu() {
			return fFlu;
		 }

		 public Sport getSport() {
			return fSport;
		 }

		 public Trav getTrav() {
			return fTrav;
		 }

		 public Uv getUv() {
			return fUv;
		 }

		 public static class Air {
			@SerializedName ("brf")
			private final String fBrf;

			@SerializedName ("txt")
			private final String fTxt;

			public Air( String pBrf, String pTxt ) {
			   this.fBrf = pBrf;
			   this.fTxt = pTxt;
			}

			public String getBrf() {
			   return fBrf;
			}

			public String getTxt() {
			   return fTxt;
			}
		 }

		 public static class Comf {
			@SerializedName ("brf")
			private final String fBrf;

			@SerializedName ("txt")
			private final String fTxt;

			public Comf( String pBrf, String pTxt ) {
			   this.fBrf = pBrf;
			   this.fTxt = pTxt;
			}

			public String getBrf() {
			   return fBrf;
			}

			public String getTxt() {
			   return fTxt;
			}
		 }

		 public static class Cw {
			@SerializedName ("brf")
			private final String fBrf;

			@SerializedName ("txt")
			private final String fTxt;

			public Cw( String pBrf, String pTxt ) {
			   this.fBrf = pBrf;
			   this.fTxt = pTxt;
			}

			public String getBrf() {
			   return fBrf;
			}

			public String getTxt() {
			   return fTxt;
			}
		 }

		 public static class Drsg {
			@SerializedName ("brf")
			private final String fBrf;

			@SerializedName ("txt")
			private final String fTxt;

			public Drsg( String pBrf, String pTxt ) {
			   this.fBrf = pBrf;
			   this.fTxt = pTxt;
			}

			public String getBrf() {
			   return fBrf;
			}

			public String getTxt() {
			   return fTxt;
			}
		 }

		 public static class Flu {
			@SerializedName ("brf")
			private final String fBrf;

			@SerializedName ("txt")
			private final String fTxt;

			public Flu( String pBrf, String pTxt ) {
			   this.fBrf = pBrf;
			   this.fTxt = pTxt;
			}

			public String getBrf() {
			   return fBrf;
			}

			public String getTxt() {
			   return fTxt;
			}
		 }

		 public static class Sport {
			@SerializedName ("brf")
			private final String fBrf;

			@SerializedName ("txt")
			private final String fTxt;

			public Sport( String pBrf, String pTxt ) {
			   this.fBrf = pBrf;
			   this.fTxt = pTxt;
			}

			public String getBrf() {
			   return fBrf;
			}

			public String getTxt() {
			   return fTxt;
			}
		 }

		 public static class Trav {
			@SerializedName ("brf")
			private final String fBrf;

			@SerializedName ("txt")
			private final String fTxt;

			public Trav( String pBrf, String pTxt ) {
			   this.fBrf = pBrf;
			   this.fTxt = pTxt;
			}

			public String getBrf() {
			   return fBrf;
			}

			public String getTxt() {
			   return fTxt;
			}
		 }

		 public static class Uv {
			@SerializedName ("brf")
			private final String fBrf;

			@SerializedName ("txt")
			private final String fTxt;

			public Uv( String pBrf, String pTxt ) {
			   this.fBrf = pBrf;
			   this.fTxt = pTxt;
			}

			public String getBrf() {
			   return fBrf;
			}

			public String getTxt() {
			   return fTxt;
			}
		 }
	  }
   }
}
