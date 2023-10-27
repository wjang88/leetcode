class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // if s2 is shorter than s1, then it's impossible to find a permutation
        if (s2.length() < s1.length())
            return false;
        
        // prepare char freq map of s1
        Map<Character,Integer> wordMap = new HashMap<>();
        for (int i=0; i<s1.length(); i++) {
            char c = s1.charAt(i);
            wordMap.put(c, wordMap.getOrDefault(c,0)+1);
        }

        // prepare char freq map of s2 for substring that is same length as s1
        Map<Character,Integer> winMap = new HashMap<>();
        int cMatched = 0;
        for (int i=0; i<s1.length(); i++) {
            char c = s2.charAt(i);
            winMap.put(c, winMap.getOrDefault(c,0)+1);
            if (wordMap.get(c)!=null) {
                if (winMap.get(c)==wordMap.get(c))
                    cMatched++;
                else if (winMap.get(c)-1==wordMap.get(c))
                    cMatched--;
            }
        }
        if (cMatched == wordMap.size())
            return true;
        
        // increment the window (same length as s1) and compare characters matched
        int winStart=0, winEnd=s1.length();
        while (winEnd < s2.length()) {
            char sC = s2.charAt(winStart);
            winMap.put(sC, winMap.get(sC)-1);
            if (wordMap.get(sC)!=null) {
                if (winMap.get(sC)==wordMap.get(sC))
                    cMatched++;
                else if (winMap.get(sC)+1==wordMap.get(sC))
                    cMatched--;
            }
            winStart++;

            char eC = s2.charAt(winEnd);
            winMap.put(eC, winMap.getOrDefault(eC,0)+1);
            if (wordMap.get(eC)!=null) {
                if (winMap.get(eC)==wordMap.get(eC))
                    cMatched++;
                else if (winMap.get(eC)-1==wordMap.get(eC))
                    cMatched--;
            }
            winEnd++;

            if (cMatched == wordMap.size())
                return true;
        }

        // if here then no substring of s2 found as a perm of s1
        return false;
    }
}
