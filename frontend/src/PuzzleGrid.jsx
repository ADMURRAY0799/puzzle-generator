import { useEffect, useState } from "react";
import axios from 'axios';

export default function PuzzleGrid() {
  const [puzzle, setPuzzle] = useState(null);

  useEffect(() => {
    axios.get('/api/puzzle')
      .then(response => setPuzzle(response.data))
      .catch(error => console.error('Error fetching puzzle: ', error));
  }, []);

  if (!puzzle) {
    return <div>Loading puzzle...</div>;
  }

  const { rows, cols, tiles } = puzzle;

  const gridStyle = {
    display: 'grid',
    gridTemplateRows: `repeat(${rows}, 1fr)`,
    gridTemplateColumns: `repeat(${cols}, 1fr)`,
    gap: '2px'
  };

  const getTileKey = (tile) => `r${tile.row}-c${tile.col}`;

  function Tile({ tile }) {
    const baseClass = "flex items-center justify-center border aspect-square text-xl font-bold";

    const getTileStyle = () => {
      switch (tile.type) {
        case 'EMPTY':
          return "bg-white";
        case 'BLOCK':
          return "bg-blue-500 text-white";
        case 'GOAL':
          return "bg-green-500 text-white";
        default:
          return "bg-gray-400";
      }
    };

    return (
      <div className={`${baseClass} ${getTileStyle()}`}>
        {tile.blockId ? tile.blockId : ''}
      </div>
    );
  }

  return (
    <div className="p-4">
      <div style={gridStyle} className="w-full max-w-lg mx-auto bg-gray-300">
        {tiles.map(tile => (
          <Tile key={getTileKey(tile)} tile={tile} />
        ))}
      </div>
    </div>
  );
}
