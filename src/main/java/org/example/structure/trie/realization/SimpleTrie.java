package org.example.structure.trie.realization;

import org.example.structure.trie.Trie;

import java.util.*;

public class SimpleTrie implements Trie {
    private static class Node {
        Map<Character, Node> children;
        boolean isWord = false;
    }

    private final Node root = new Node();

    @Override
    public void insert(String word) {
        Node curNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            char s = word.charAt(i);
            if (curNode.children == null) curNode.children = new HashMap<>();
            Node child = curNode.children.get(s);
            if (child == null) {
                child = new Node();
                curNode.children.put(s, child);
            }
            curNode = child;
        }
        curNode.isWord = true;
    }

    public List<String> searchByPrefix(String prefix) {
        Node curNode = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char s = prefix.charAt(i);
            Node child = curNode.children.get(s);
            if (child == null) return List.of();
            curNode = child;
        }
        List<String> res = getAllWordsFromNode(curNode, prefix);
        Collections.sort(res);
        return res;
    }

    private List<String> getAllWordsFromNode(Node node, String prefix) {
        List<String> words = new ArrayList<>();
        if (node.isWord) words.add(prefix);
        if (node.children != null) {
            for (var entry : node.children.entrySet()) {
                words.addAll(getAllWordsFromNode(entry.getValue(), prefix + entry.getKey()));
            }
        }
        return words;
    }
}
