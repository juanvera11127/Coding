using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawner : MonoBehaviour
{
	public GameObject[] spawnPoints;
	public GameObject[] enemies;
	public int spawnedEnemies;
	public float rate = 3;
	public int spawnLimit = 5;
	private float timer = 0;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
		timer += Time.deltaTime;
		if(timer > rate)
		{
			timer = 0;
			Spawn();
		}
    }
	void Spawn()
	{
		if(spawnedEnemies < spawnLimit)
		{
			GameObject lastSpawn = Instantiate(enemies[Random.Range(0, enemies.Length)], spawnPoints[Random.Range(0, spawnPoints.Length)].transform.position, transform.rotation);
			lastSpawn.GetComponent<Enemy>().mySpawner = this;
			spawnedEnemies += 1;
		}
	}
}
