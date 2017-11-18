'''
Isse Nur
CSCI 1913
Project 1
04-oct-17 '''

class Random:

    def __init__(self,seed):
        self.n = seed

    def next(self, range):
        self.n = (16807 * self.n)%(2147483647)
        return self.n % range

    def choose(self, characters):
        chosen = self.next(len(characters))
        return characters[chosen]

class Words:

    def __init__(self, seed):
        self.first=""
        self.follow = {}
        self.random = Random(seed)

    def add(self, word):
        i = 0
        self.first = word[0] + self.first
        while (i<len(word)-1):
            if word[i] in self.follow:
                self.follow[word[i]] = self.follow[word[i]] + word[i+1]
                i= i+1
            else:
                self.follow[word[i]] = word[i+1]
                i= i+1
    def make(self, size):
        lookup = self.random.choose(self.first)
        i = 1
        first2 = lookup
        while (i<size):
            if lookup in self.follow:
                lookup = self.random.choose(self.follow[lookup])
            else:
                lookup = self.random.choose(self.first)
            first2 =  first2 + lookup
            i = i + 1
        return first2

prez = Words(101)
prez.add('Abdihafit')
prez.add('Zakaria')
prez.add('Isse')
prez.add('Hussein')
prez.add('Ahmed')
prez.add('Nur')

prez.make(7)
