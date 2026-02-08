// ec/edu/espe/model/SortingModel.js

// --- ESTRATEGIA 1: BUBBLE SORT ---
class BubbleSort {
    sort(data) {
        let arr = [...data]; // Copia del arreglo
        let n = arr.length;
        for (let i = 0; i < n - 1; i++) {
            for (let j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    let temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}

// --- ESTRATEGIA 2: INSERTION SORT ---
class InsertionSort {
    sort(data) {
        let arr = [...data];
        let n = arr.length;
        for (let i = 1; i < n; i++) {
            let key = arr[i];
            let j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}

// --- ESTRATEGIA 3: QUICK SORT ---
class QuickSort {
    sort(data) {
        let arr = [...data];
        this.quickSortRecursive(arr, 0, arr.length - 1);
        return arr;
    }

    quickSortRecursive(arr, low, high) {
        if (low < high) {
            let pi = this.partition(arr, low, high);
            this.quickSortRecursive(arr, low, pi - 1);
            this.quickSortRecursive(arr, pi + 1, high);
        }
    }

    partition(arr, low, high) {
        let pivot = arr[high];
        let i = low - 1;
        for (let j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                [arr[i], arr[j]] = [arr[j], arr[i]];
            }
        }
        [arr[i + 1], arr[high]] = [arr[high], arr[i + 1]];
        return i + 1;
    }
}

// --- CONTEXTO ---
class SortingContext {
    constructor() {
        this.strategy = null;
    }

    setStrategy(strategy) {
        this.strategy = strategy;
    }

    sort(data) {
        if (!this.strategy) {
            throw new Error("Estrategia no definida");
        }
        return this.strategy.sort(data);
    }

    getStrategyName() {
        return this.strategy.constructor.name;
    }
}

// Exportamos las clases para que el Controlador las pueda usar
module.exports = { BubbleSort, InsertionSort, QuickSort, SortingContext };