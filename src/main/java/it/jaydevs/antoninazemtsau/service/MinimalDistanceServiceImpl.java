package it.jaydevs.antoninazemtsau.service;
public class MinimalDistanceServiceImpl implements MinimalDistanceService {
    @Override
    public int minimalDistance(String inputWord, String targetWord) {
        if (inputWord == null || targetWord == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        var wordData = new WordsData(inputWord, targetWord, new int[inputWord.length() + 1][targetWord.length() + 1]);

        int distance = calculateDistance(wordData);

        System.out.println("Distance between words - " + distance);

        transformationWordProcessPrint(inputWord, targetWord, wordData, distance);
        return wordData.distanceMatrix[wordData.getInputWordLength()][wordData.getTargetWordLength()];
    }

    private void transformationWordProcessPrint(String inputWord, String targetWord, WordsData wordData, int distance) {
        int currentIndexI = wordData.getInputWordLength();
        int currentIndexJ = wordData.getTargetWordLength();
        String[] modifiedTargetWord = targetWord.split("");
        System.out.println("Transformation process:");
        printResult(modifiedTargetWord);

        while (distance > 0 && currentIndexI > 0 && currentIndexJ > 0) {
            int deletion = wordData.distanceMatrix[currentIndexI][currentIndexJ - 1];
            int insertion = wordData.distanceMatrix[currentIndexI - 1][currentIndexJ];
            int substitution = wordData.distanceMatrix[currentIndexI - 1][currentIndexJ - 1];
            if (substitution < distance) {
                System.out.println("Substitution");
                modifiedTargetWord[currentIndexJ - 1] = Character.toString(inputWord.charAt(currentIndexI - 1));
                currentIndexI--;
                currentIndexJ--;
                distance = substitution;
            } else if (deletion < distance) {
                System.out.println("Deletion");
                modifiedTargetWord[currentIndexJ - 1] = "";
                currentIndexJ--;
                distance = deletion;
            } else if (insertion < distance) {
                System.out.println("Insertion");
                modifiedTargetWord = insertIntoArray(modifiedTargetWord, currentIndexJ, Character.toString(inputWord.charAt(currentIndexI - 1)));
                currentIndexI--;
                distance = insertion;
            } else {
                currentIndexI--;
                currentIndexJ--;
            }
            printResult(modifiedTargetWord);
        }
    }

    private static void printResult(String[] modifiedTargetWord) {
        System.out.println(String.join("", modifiedTargetWord));
    }

    private int calculateDistance(WordsData wordsData) {
        int[][] distanceMatrix = initializeMatrix(wordsData);
        fillMatrixWithTransformationData(wordsData, distanceMatrix);
        return distanceMatrix[wordsData.getInputWordLength()][wordsData.getTargetWordLength()];
    }

    private void fillMatrixWithTransformationData(WordsData wordsData, int[][] distanceMatrix) {
        for (int i = 1; i <= wordsData.getInputWordLength(); i++) {
            for (int j = 1; j <= wordsData.getTargetWordLength(); j++) {
                int deletion = distanceMatrix[i][j - 1] + 1;
                int insertion = distanceMatrix[i - 1][j] + 1;
                int substitution = distanceMatrix[i - 1][j - 1] + (wordsData.inputWord.charAt(i - 1)
                        == wordsData.targetWord.charAt(j - 1) ? 0 : 1);
                distanceMatrix[i][j] = Math.min(Math.min(deletion, insertion), substitution);
            }
        }
    }

    private int[][] initializeMatrix(WordsData wordsData) {
        int[][] distanceMatrix = wordsData.distanceMatrix;
        for (int i = 0; i <= wordsData.getInputWordLength(); i++) {
            distanceMatrix[i][0] = i;
        }
        for (int j = 0; j <= wordsData.getTargetWordLength(); j++) {
            distanceMatrix[0][j] = j;
        }
        return distanceMatrix;
    }

    private String[] insertIntoArray(String[] arr, int index, String newItem) {
        String[] result = new String[arr.length + 1];
        if (index >= 0) System.arraycopy(arr, 0, result, 0, index);
        result[index] = newItem;
        if (result.length - (index + 1) >= 0)
            System.arraycopy(arr, index + 1 - 1, result, index + 1, result.length - (index + 1));
        return result;
    }

    private record WordsData(String inputWord, String targetWord, int[][] distanceMatrix) {
        public int getInputWordLength() {
            return inputWord.length();
        }
        public int getTargetWordLength() {
            return targetWord.length();
        }
    }
}
