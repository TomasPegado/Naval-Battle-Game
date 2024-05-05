// package model;

// public class ShotResult {
// private boolean hit;
// private boolean sunk;
// private ShipType shipType;

// // Construtor padrão para um tiro que não atinge um navio
// public ShotResult() {
// this.hit = false;
// this.sunk = false;
// this.shipType = null;
// }

// // Construtor para um tiro que atinge um navio
// public ShotResult(boolean hit, boolean sunk, ShipType shipType) {
// this.hit = hit;
// this.sunk = sunk;
// this.shipType = shipType;
// }

// // Métodos de acesso
// public boolean isHit() {
// return hit;
// }

// public boolean isSunk() {
// return sunk;
// }

// public ShipType getShipType() {
// return shipType;
// }

// // Métodos de modificação (opcional)
// // public void setHit(boolean hit) {
// // this.hit = hit;
// // }

// // public void setSunk(boolean sunk) {
// // this.sunk = sunk;
// // }

// // public void setShipType(ShipType shipType) {
// // this.shipType = shipType;
// // }

// // Método toString para representar o resultado em texto, útil para depuração
// @Override
// public String toString() {
// if (!hit) {
// return "Shot missed. Hit water.";
// } else {
// String result = "Shot hit a " + shipType;
// if (sunk) {
// result += ", which was sunk!";
// }
// return result;
// }
// }
// }
