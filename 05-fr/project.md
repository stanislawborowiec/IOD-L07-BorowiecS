# System aukcyjny

## Wprowadzenie

Specyfikacja wymagań funkcjonalnych w ramach informatyzacji procesu sprzedaży produktów w oparciu o mechanizm aukcyjny. 

## Procesy biznesowe

---
<a id="bc1"></a>
### BC1: Sprzedaż aukcyjna 

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Opis:** Proces biznesowy opisujący sprzedaż za pomocą mechanizmu aukcyjnego. |

**Scenariusz główny:**
1. [Sprzedający](#ac1) wystawia produkt na aukcję. ([UC1](#uc1))
2. [Kupujący](#ac2) oferuje kwotę za produkt wyższą od aktualnie najwyższej oferty. ([BR1](#br1))
3. [Kupujący](#ac2) wygrywa aukcję ([BR2](#br2))
4. [Kupujący](#ac2) przekazuje należność Sprzedającemu.
5. [Sprzedający](#ac1) przekazuje produkt Kupującemu.

**Scenariusze alternatywne:** 

2.A. Oferta Kupującego została przebita, a [Kupujący](#ac2) pragnie przebić aktualnie najwyższą ofertę.
* 2.A.1. Przejdź do kroku 2.

3.A. Czas aukcji upłynął i [Kupujący](#ac2) przegrał aukcję. ([BR2](#br2))
* 3.A.1. Koniec przypadku użycia.

---

## Aktorzy

<a id="ac1"></a>
### AC1: Sprzedający

Osoba oferująca towar na aukcji.

<a id="ac2"></a>
### AC2: Kupujący

Osoba chcąca zakupić produkt na aukcji.


## Przypadki użycia poziomu użytkownika

### Aktorzy i ich cele

[Sprzedający](#ac1):
* [UC1](#uc1): Wystawienie produktu na aukcję
* Otrzymanie należności
* Przekazanie produktu [Kupującemu](#ac2)

[Kupujący](#ac2)
* [BR1](#br1): Złożenie oferty
* [BR2](#br2): Rozstrzygnięcie aukcji
* Przekazanie należności [Sprzedającemu](#ac1)


---
<a id="uc1"></a>
### UC1: Wystawienie produktu na aukcję

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) zgłasza do systemu chęć wystawienia produktu na aukcję.
2. System prosi o podanie danych produktu i ceny wywoławczej.
3. [Sprzedający](#ac1) podaje dane produktu oraz cenę wywoławczą.
4. System weryfikuje poprawność danych.
5. System informuje o pomyślnym wystawieniu produktu na aukcję.

**Scenariusze alternatywne:** 

4.A. Podano niepoprawne lub niekompletne dane produktu.
* 4.A.1. System informuje o błędnie podanych danych.
* 4.A.2. Przejdź do kroku 2.

---

<a id="uc2"></a>
### UC2: Złożenie oferty kupna

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) wyraża chęć zakupu produktu.
2. System prosi o podanie oferty [Kupującego](#ac2)
3. [Kupujący](#ac2) wprowadza ofertę do systemu.
4. System weryfikuje poprawność oferty.
5. System zmienia obecną cenę produktu.
6. System informuje o pomyślnie złożonej ofercie.

**Scenariusze alternatywne:** 

1.A. Aukcja jest zakończona.
* 1.A.1. System informuje [Kupującego](#ac2) o niedostępności produktu.

4.A. Oferta jest mniejsza od aktualnej ceny produktu.
* 4.A.1. System informuje [Kupującego](#ac2) o zbyt niskiej ofercie.
* 4.A.2. Przejdź do kroku 2.

4.B. Oferta nie jest większa od sumy obecnej oferty i [minimalnej kwoty przebicia](#br1).
* 4.B.1. System informuje [Kupującego](#ac2) o [minimalnej kwocie przebicia](#br1).
* 4.B.2. Przejdź do kroku 2.

5.A. Obecna kwota produktu uległa zmianie podczas wprowadzania oferty.
* 5.A.1. System informuje o zmianie obecnej ceny i wyświetla cenę aktualną.
* 5.A.2. Przejdź do kroku 2.

---

<a id="uc3"></a>
### UC3: Zakończenie aukcji

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Scenariusz główny:**
1. System blokuje wprowadzanie nowych ofert do aukcji.
2. System wybiera kupującego, który w momencie zakończenia aukcji posiadał najwyższą ofertę.
3. System informuje [Sprzedającego](#ac1) i [Kupującego](#ac2) o zakończeniu oferty.
4. System wymienia dane między [Sprzedającym](#ac1) a [Kupującym](#ac2). 
5. System prosi obie strony o potwierdzenie otrzymania danych i finalizacji oferty.
6. System informuje o pomyślnym zakończeniu aukcji.
7. System archiwizuje aukcję.

**Scenariusze alternatywne:** 

2.A. Oferta nie ma kupujących.
* 2.A.1. System informuje [Sprzedającego](#ac1) o braku zainteresowanych ofertą.
* 2.A.2. Przejdź do kroku 7.

5.A. Brak potwierdzenia otrzymania danych.
* 5.A.1. Przejdź do kroku 4.

---

<a id="uc4"></a>
### UC4: Anulowanie aukcji

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) informuje system o chęci anulowania aukcji.
2. System blokuje wprowadzanie nowych ofert do aukcji.
3. System informuje [Sprzedającego](#ac1) o zakończeniu aukcji.
4. System archiwizuje aukcję.

---

## Obiekty biznesowe (inaczej obiekty dziedzinowe lub informatyczne)

### BO1: Aukcja

Aukcja jest formą zawierania transakcji kupna-sprzedaży, w której Sprzedający określa cenę wywoławczą produktu, natomiast Kupujący mogą oferować własną ofertę zakupu każdorazowo proponując kwotę wyższą od aktualnie oferowanej kwoty. Aukcja kończy się po upływie określonego czasu. Jeśli złożona została co najmniej jedna oferta zakupy produkt nabywa ten Kupujący, który zaproponował najwyższą kwotę. 

### BO2: Produkt

Fizyczny lub cyfrowy obiekt, który ma zostać sprzedany w ramach aukcji.

### BO3: Użytkownik

Osoba w systemie, która ma określone dane i może być [Sprzedającym](#ac1), lub [Kupującym](#ac2).

### BO4: Aukcja zarchiwizowana

Aukcja, która dobiegła końca i została zarchiwizowana.

## Reguły biznesowe

<a id="br1"></a>
### BR1: Złożenie oferty

Złożenie oferty wymaga zaproponowania kwoty wyższej niż aktualnie oferowana o minimum 1,00 PLN.


<a id="br2"></a>
### BR2: Rozstrzygnięcie aukcji

Aukcję wygrywa ten z [Kupujący](#ac2)ch, który w momencie jej zakończenia (upłynięcia czasu) złożył najwyższą ofertę.

## Macierz CRUDL


| Przypadek użycia    | Aukcja | Produkt | Użytkownik | Aukcja zarchiwizowana |
| ---------------------------------------- | --- |--- | -- |--- |
| UC1: Wystawienie produktu na aukcję      |  C  |  C | -  |  - |
| UC2: Złożenie oferty kupna               |  R  |  - | U  |  - |
| UC3: Zakończenie aukcji                  | RUD | R  | RU |  C |
| UC4: Anulowanie aukcji                   | UD  | -  | U  |  C |

