using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Lifespan : MonoBehaviour
{
	public float lifeSpan = 0.2f;
	public bool DDRResult = false;
    // Start is called before the first frame update
    void Start()
    {
		Destroy(gameObject, lifeSpan);
    }

    // Update is called once per frame
    void Update()
    {
		if (DDRResult)
			transform.Translate(0, Time.deltaTime * 150f, 0);
    }
}
