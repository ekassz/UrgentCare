//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    UrgentCareAdmissions Tester
// Course:   CS 300 Spring 2023
//
// Author:   Emili Robles
// Email:    ejrobles@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    no partner
// Partner Email:   n/a
// Partner Lecturer's Name: n/a
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: no one
// Online Sources: https://www.gradescope.com/courses/489016/assignments/2614697/submissions/158889246
// Gradescope helped to see what I needed, after I used geeksforgeeks.org to 
// understand how to implement my methods and testers.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays; // consider using Arrays.deepEquals() to test the contents of a 2D array

// Javadoc style class header comes here
public class UrgentCareAdmissionsTester {

  /**
   * This test method is provided for you in its entirety, to give you a model for the rest of this
   * class. This method tests the getAdmissionIndex() method on a non-empty, non-full array of
   * patient records which we create and maintain here.
   * 
   * This method tests three scenarios:
   * 
   *   1. Adding a patient with a HIGHER triage priority than any currently present in the array. 
   *   To do this, we create an array with no RED priority patients and get the index to add a RED
   *   priority patient. We expect this to be 0, so if we get any other value, the test fails.
   *   
   *   2. Adding a patient with a LOWER triage priority than any currently present in the array. 
   *   To do this, we create an array with no GREEN priority patients and get the index to add a 
   *   GREEN priority patient. We expect this to be the current size of the oversize array, so if 
   *   we get any other value, the test fails.
   *   
   *   3. Adding a patient with the SAME triage priority as existing patients. New patients at the
   *   same priority should be added AFTER any existing patients. We test this for all three triage
   *   levels on an array containing patients at all three levels.
   * 
   * @author hobbes
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testGetIndex() {

    // The non-empty, non-full oversize arrays to use in this test.
    // Note that we're using the UrgentCareAdmissions named constants to create these test records,
    // rather than their corresponding literal int values. 
    // This way if the numbers were to change in UrgentCareAdmissions, our test will still be valid.
    int[][] patientsListAllLevels = new int[][] {
      {32702, 3, UrgentCareAdmissions.RED},
      {21801, 2, UrgentCareAdmissions.YELLOW},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      {31501, 1, UrgentCareAdmissions.GREEN},
      null, null, null
    };
    int allLevelsSize = 5;

    int[][] patientsListOnlyYellow = new int[][] {
      {21801, 2, UrgentCareAdmissions.YELLOW},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      null, null, null, null, null
    };
    int onlyYellowSize = 3;

    // scenario 1: add a patient with a higher priority than any existing patient
    {
      int expected = 0;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, 
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual) return false;
    }

    // scenario 2: add a patient with a lower priority than any existing patient
    {
      int expected = onlyYellowSize;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, 
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual) return false;
    }

    // scenario 3: verify that a patient with the same priority as existing patients gets
    // added after all of those patients
    {
      int expected = 1;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, 
          patientsListAllLevels, allLevelsSize);
      if (expected != actual) return false;
      
      expected = 4;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, 
          patientsListAllLevels, allLevelsSize);
      if (expected != actual) return false;
      
      expected = allLevelsSize;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, 
          patientsListAllLevels, allLevelsSize);
      if (expected != actual) return false;
    }

    // and finally, verify that the arrays were not changed at all
    {
      int[][] allLevelsCopy = new int[][] {
        {32702, 3, UrgentCareAdmissions.RED},
        {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW},
        {31501, 1, UrgentCareAdmissions.GREEN},
        null, null, null
      };
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy)) return false;

      int[][] onlyYellowCopy = new int[][] {
        {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW},
        null, null, null, null, null
      };
      if (!Arrays.deepEquals(patientsListOnlyYellow, onlyYellowCopy)) return false;
    }

    return true;
  }
  
 
  
  /**
   * Tests the behavior of the addPatient method using a non-empty, non-full array. Each test 
  // should verify that the returned size is as expected and that the array has been updated 
  // (or not) appropriately
   * 
   * Goes over 3 things:
   * 1. Adding a patient to the end of the list by first checking if the list has
   * increased by 1 
   * 2. adds patient2 to the middle of the list index 1 and checks if the size
   * the list has also increased by 1
   * 3. adds patient3 using an invalid index, so it checks if the size of list is
   * the same
   * 
   * @author erobl
   * @return true if and only if it passes all tests
   */
  public static boolean testAddPatient() {
    int[] patient1 = {12345, 1, 2};
    int[] patient2 = {34567, 2, 3};
    int[] patient3 = {11111, 3, 1};
    int[][] patientsList = new int[10][3]; //random size created to test 3 examples
    int size = 0;
    
    // (1) add a patient to the END of the patientsList
    size = UrgentCareAdmissions.addPatient(patient1, size, patientsList, size);
    if(size !=1 || patientsList[0][0] != 12345) { //checking if increased by 1 and adds
      return false;  
      
    }
    // (2) add a patient to the MIDDLE of the patientsList
    size = UrgentCareAdmissions.addPatient(patient2, 1, patientsList, size);
    if(size != 2 || patientsList[1][0] != 34567) { 
      return false;
    }
    
    
    // (3) add a patient using an invalid (out-of-bounds) index
    size = UrgentCareAdmissions.addPatient(patient3, 5, patientsList, size);
    if(size != 2) {
      return false;
    }
    return true; //yay you added a patient
  }


  /**
   * Tests the behavior of the removeNextPatient method using a non-empty, non-full array. Each 
   * test should verify that the returned size is as expected and that the 
   * array has been updated (or not) appropriately
   * 
   *This check the following:
   *1. First we add 2 patients for examples to then remove the first patient
   *2. Then checks if the second patient is there to remove
   *
   * @author erobl
   * @return true if all tests pass
   * 
   */
  public static boolean testRemovePatient() {
    int[] patient1 = {12345, 1, 2};
    int[] patient2 = {34567, 2, 3};
    int[][] patientsList = new int[10][3]; //examples to test / remove
    int size = 0;
    // (1) remove a patient from a patientsList containing more than one record
    size = UrgentCareAdmissions.addPatient(patient1, size, patientsList, size);
    size = UrgentCareAdmissions.addPatient(patient2, size, patientsList, size);
    size = UrgentCareAdmissions.removeNextPatient(patientsList, size);
    
    if(size != 1 || patientsList[0][0] != 34567) {
      return false;
    }
    // (2) remove a patient from a patientsList containing only one record
    size = UrgentCareAdmissions.removeNextPatient(patientsList, size);
    if(size != 0) {
    return false;
  }
    return true;
  }
  
  /**
   * Tests the behavior of the getPatientIndex method
   * using a non-empty, non-full array.
   * 
   * Steps for this:
   * 1. Looks for a patient at end of the list, in this case its looking case#
   * 11111, if it finds then test passed
   * 2. Looking for the second patient case # , if it finds it then passes
   * 3. Looking for a patient that's not in the list, so it should return -1,
   * if it does then it passes onto true.
   * 
   * @author erobl
   * @return true if all tests to get index pass 
   */
  public static boolean testGetPatientIndex() {
    int [][] patientsList = {
        {12345, 0,0,0,0},
        {54321, 0,0,0,0},
        {11111, 0,0,0,0} 
        };
    int size = 3;
    
    
    // (1) look for a patient at the end of the list
    int caseID1 = 11111;
    int expected = 2;
    int actual = UrgentCareAdmissions.getPatientIndex(caseID1, patientsList, size);
    
    if(actual != expected) {
      return false;
    }
    // (2) look for a patient in the middle of the list
    int caseID2 = 54321;
    int expected2 = 1;
    int actual2 = UrgentCareAdmissions.getPatientIndex(caseID2, patientsList, size);
    
    if(actual2 != expected2) {
      return false;
    }
    
    // (3) look for a patient not present in the list
    int caseID3 = 33333;
    int expected3 = -1;
    int actual3 = UrgentCareAdmissions.getPatientIndex(caseID3, patientsList, size);
    
    if(actual3 != expected3) {
      return false;
    }
    return true;
  }
  
  /**
   * Tests the getLongestWaitingPatientIndex method using a non-empty, non-full array. When
  // designing these tests, recall that arrivalOrder values will all be unique!
   * 
   * Steps into doing this:
   * 1. Testing different size of lists, first we do one patient and if the
   * value is correct then it passes
   * 2. Same thing but now we do it with 3 patients 
   * 
   * @author erobl
   * @return true if all tests pass 
   */
  public static boolean testLongestWaitingPatient() {
    // (1) call the method on a patientsList with only one patient
    int [][] patientsList = {{12345, 10,0,0,0}};
    int size = 1;
    int expected = 0;
    int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsList, size);
    
    if(actual != expected) {
      return false;
    }
    
    // (2) call the method on a patientsList with at least three patients
    int [][] patientsListSecond = {
        {12345, 10,0,0,0},
        {54321, 0,0,0,0},
        {11111, 0,0,0,0}
        };
    int sizeSecond = 3;
    int expectedSecond = 1;
    int actualSecond = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListSecond, sizeSecond);
    
    if(actualSecond != expectedSecond) {
      return false;
    }
    return true;
  }
  
  
  /**
   * Tests the edge case behavior of the UrgentCareAdmissions methods
   * using empty and full arrays
   * 
   * Wanted to test all class method with empty/full arrays but honestly its a lot
   * ended up doing too much. 
   * Is supposed to check getAdmissionIndex and when its empty or full
   * 
   * 
   * @author erobl
   * @return true if all tests pass
   */
  
  public static boolean testEmptyAndFullArrays() {
    
    // (1) test getAdmissionIndex using an empty patientsList array and any triage level
    int [][] emptyPatientsList = {null, null, null, null, null, null, null, null};
    int emptySize = 0;
    int triage = 2;
    int actual = UrgentCareAdmissions.getAdmissionIndex(triage, emptyPatientsList, emptySize);
    
    if(actual != -1) {
      return false;
    }
    // (2) test getAdmissionIndex using a full patientsList array and any triage level
    int triageFull = -1;
    int[][] patientsList = {
        {1,2,0},
        {3,4,1},
        {4,5,6}
    };
    int size = 3;
    int actual2 = UrgentCareAdmissions.getAdmissionIndex(triageFull, emptyPatientsList, emptySize);
    
    if(actual2 != 0) {
      return false;
    }
    // (3) test addPatient using a full patientsList array
    
    int[] patientRecordExample = {6,7,8};
    int index = UrgentCareAdmissions.getAdmissionIndex(patientRecordExample[2], patientsList, size);
    int actual3 = UrgentCareAdmissions.addPatient(patientRecordExample, index, patientsList, size);
    
    if(actual3 != 0) {
      return false;
    }
    
    // (4) test removeNextPatient using an empty patientsList array
    int actual4 = UrgentCareAdmissions.removeNextPatient(emptyPatientsList, emptySize);
    
    if(actual4 != 0) {
      return false;
    }
    // (5) test getPatientIndex using an empty patientsList array
    int caseID = 2;
    int actual5 = UrgentCareAdmissions.getPatientIndex(caseID, emptyPatientsList, emptySize);
    
    if( actual5 != -1) {
      return false;
    }
    // (6) test getLongestWaitingPatientIndex using an empty patientsList array
    int [][] longestWaitingPatientsList = {null, null, null, null, null, null, null, null};
    int longestWaitingPatientSize = 0;
    int actual6 = UrgentCareAdmissions.getLongestWaitingPatientIndex(longestWaitingPatientsList, longestWaitingPatientSize);
    
    if(actual6 != -1) {
      return false;
    }
    return true;
    
  }
  
  
  /**
   * Tests the getSummary method using arrays in various states
   * 
   * Tests with empty list, list that has multiple people at a time but in just
   * 1 triage level and a list with people from all 3 triage levels
   * 
   * @author erobl
   * @return true if all tests pass
   */

  public static boolean testGetSummary() {
    
  
    // (1) test getSummary using an empty patientsList
    int [][] patientsListEx = {};
    int size1 = 0;
    String expected = "No patients in list";
    String actual = UrgentCareAdmissions.getSummary(patientsListEx, size1);
    
    if(!actual.equals(expected)) {
      return false;
    }
        
    
    // (2) test getSummary using a patientsList with multiple patients at a single triage level
    int[][] patientsListEx2 = {
        {12345, 1, 0},
          {54321, 2, 0},
          {11111, 3, 0}
        };
    int size2 = 3;
    String expected2 = "Total number of patients: 3\nRED: 3\nYELLOW: 0\nGREEN: 0";
    String actual2 = UrgentCareAdmissions.getSummary(patientsListEx2, size2);
    
    if(!actual2.equals(expected2)) {
      return false;
    }
    // (3) test getSummary using a patientsList with patients at all triage levels
    int[][] patientsListEx3 = {
        {12345, 1, 0},
          {54321, 2, 1},
          {11111, 3, 2}
        };
    int size3 = 3;
    String expected3 = "Total number of patients: 3\nRED: 1\nYELLOW: 1\nGREEN: 1";
    String actual3 = UrgentCareAdmissions.getSummary(patientsListEx3, size3);
    
    if(!actual3.equals(expected3)) {
      return false;
    }
   
    return true;
}
  /**
   * Runs each of the tester methods and displays their result
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("get index: "+testGetIndex());
    System.out.println("add patient: "+testAddPatient());
    System.out.println("remove patient: "+testRemovePatient());
    System.out.println("get patient: "+testGetPatientIndex());
    System.out.println("longest wait: "+testLongestWaitingPatient());
    System.out.println("empty/full: "+testEmptyAndFullArrays());
    System.out.println("get summary: "+testGetSummary());
  }

}
