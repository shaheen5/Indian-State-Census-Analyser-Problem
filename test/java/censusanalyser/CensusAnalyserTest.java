package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.pdf";
    private static final String CSV_FILE_WITH_WRONG_DELIMITER = "./src/main/resources/IndiaStateCensusData.csv";
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFilePath_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_WhenCorrectButDelimiterWrong_ShouldThrowException() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CSV_FILE_WITH_WRONG_DELIMITER);
        } catch (CensusAnalyserException x) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_HEADER_PROBLEM, x.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_WhenCorrectButCSVHeaderIncorrect_ShouldThrowException() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CSV_FILE_WITH_WRONG_DELIMITER);
        } catch (CensusAnalyserException x) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_HEADER_PROBLEM, x.type);
        }
    }
    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int numOfRecords = stateCensusAnalyser.loadStateCodeCensusData("test/resources/IndiaStateCode.csv");
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenStateCodeInformation_WithWrongFilePath_ShouldThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            stateCensusAnalyser.loadStateCodeCensusData("./src/main/java/resources/IndiaStateCode.csv");
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}